/**
 * jspy-echarts 0.1
 */
!function ($) {

    const REQUEST_START_TIME = Date.now() - 60 * 60 * 1000;

    const REQUEST_TIMEOUT = 3 * 60 * 1000;

    const DEFAULT_REQUEST_INTERVAL = 6 * 1000;

    let JSpyCharts = function (element, options, cb) {

        let hasOptions = typeof options == 'object';

        this.url = "";

        this.interval = DEFAULT_REQUEST_INTERVAL;

        this.formatB = false;
        /**
         * 0. 初始化
         * 1. 已构建
         * 2. 已清理
         * 3. 已销毁
         */
        this.state = 0;

        this.element = element;

        this.chart = echarts.init(document.getElementById(this.element), 'macarons');

        // 下面这三个属性是用来作为标记，以判定是否继续进行递归调用
        this.target = "";
        this.startTime = REQUEST_START_TIME;
        this.endTime = -1;

        this.cb = function () {
        };

        if (hasOptions) {
            if (typeof options.url == 'string')
                this.url = options.url;

            if (typeof options.target == 'string')
                this.target = options.target;

            if (typeof options.interval == 'number')
                this.interval = options.interval;

            if (typeof options.formatB == 'boolean')
                this.formatB = options.formatB;

            if (typeof cb == 'function')
                this.cb = cb
        }

    };


    JSpyCharts.prototype = {

        constructor: JSpyCharts,

        /**
         * 加载报表
         */
        load: function () {
            this.chart.showLoading();
            this.obtainData(this.target);
        },

        /**
         * 获取数据
         *
         * @param target 查询条件
         * @param startTime 开始时间
         * @param endTime   结束时间
         */
        obtainData: function (target, startTime, endTime) {

            if (target !== this.target)
                return;

            startTime = this.startTime;
            endTime = this.endTime;

            let chart = this;
            let continuously = false;
            let tmp = endTime;

            if (endTime <= 0) {
                continuously = true;
                tmp = Date.now();
            }

            $.ajax({
                type: "post",
                contentType: "application/json",
                url: this.url,
                async: true,
                data: JSON.stringify({
                    target: target,
                    startTime: startTime,
                    endTime: tmp
                }),
                dataType: "json",
                timeout: REQUEST_TIMEOUT,
                success: function (result) {
                    if (target !== chart.target) {
                        return;
                    }

                    if (chart.state === 0 || chart.state === 3) {
                        chart.buildChart(result)
                    } else {
                        chart.refreshData(result);
                    }

                    if (continuously && chart.state < 3) {
                        setTimeout(function () {
                            if (target !== chart.target)
                                return;
                            if (endTime !== chart.endTime)
                                return;

                            chart.startTime = tmp;
                            chart.endTime = -1;
                            chart.obtainData(target, tmp, -1);
                        }, chart.interval);
                    }
                },
                error: function (errorMsg) {
                    console.log("图表请求数据失败:" + errorMsg.toString());
                    setTimeout(function () {
                        chart.obtainData(target, startTime, endTime);
                    }, REQUEST_TIMEOUT); // 请求失败，3分钟后再重新尝试连接
                }
            });
        },


        /**
         * 根据查询结果，刷新报表
         *
         * @param queryResult 查询结果
         */
        refreshData: function (queryResult) {
            let series = this.chart.getOption().series;

            for (let i = 0; i < series.length; i++) {
                for (let j = 0; j < queryResult.series[i].data.length; j++) {
                    series[i].data.shift();
                    series[i].data.push(queryResult.series[i].data[j]);
                }
            }

            this.chart.setOption({
                series: series
            })
        },


        /**
         * 构建报表
         *
         * @param queryResult 查询结果
         */
        buildChart: function (queryResult) {
            if (this.state === 3) {
                this.chart.clear();
            }

            let opt = this.initOption();

            opt.title.text = queryResult.title;
            opt.series = queryResult.series;
            opt.legend.data = queryResult.legend;
            opt.legend.selected = queryResult.legendUnSelected;

            this.chart.setOption(opt, true, false);
            this.state = 1;
            this.chart.hideLoading();
        },


        /**
         * 初始配置项
         */
        initOption: function () {
            let chart = this;
            // 指定图表的配置项和数据
            return {
                tooltip: {
                    // 当trigger为’item’时只会显示该点的数据，为’axis’时显示该列下所有坐标轴所对应的数据。
                    trigger: 'axis',
                    formatter: function (params) {
                        let res = '<p>时间：' + new Date(params[0].axisValue * 1).format("MM-dd HH:mm") + '</p>';
                        for (let i = 0; i < params.length; i++) {
                            let sizeVal = params[i].data[1] * 1;

                            let size = sizeVal;
                            if (chart.formatB) {
                                let kStr = ' - ' + Math.round(sizeVal / 1024).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') + ' K';
                                if (sizeVal < 0) {
                                    kStr = ' - ' + sizeVal;
                                }
                                size = sizeVal.formatSize() + kStr;
                            }
                            res += '<p>' + params[i].seriesName + '：' + size + '</p>'
                        }
                        return res;
                    }
                },
                title: {},
                legend: {},
                yAxis: {
                    type: 'value',
                    axisLabel: {
                        formatter: function (value) {
                            return chart.formatB ? (1 * value).formatSize() : value;
                        }
                    }
                },
                xAxis: {
                    type: 'time'
                },
                grid: {
                    show: true,
                    bottom: 72
                },
                dataZoom: [{
                    start: 0,
                    end: 100
                }]
            };
        },

        /**
         * 更新报表信息
         */
        refresh: function (target, starTime, endTime) {
            this.target = target;
            this.state = 3;
            this.startTime = starTime || REQUEST_START_TIME;
            this.endTime = endTime || Date.now();
            this.chart.showLoading();
            this.obtainData(target);
        },

        //--------------------------------------
    };


    $.fn.jspyCharts = function (options, cb) {
        let ele = this.prop('id');
        return new JSpyCharts(ele, options, cb);
    };

}(window.jQuery);


function initAndLoadJSpyChart(ele, url, target, formatB) {

    formatB = formatB || false;

    let chart = ele.jspyCharts({
        target: target,
        url: url,
        formatB: formatB
    });

    chart.load();
    return chart;
}