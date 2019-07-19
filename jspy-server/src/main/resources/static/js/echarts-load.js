const REQUEST_START_TIME = new Date().getTime() - 60 * 60 * 1000;


function loadChartContinuously(chart, url, condition, formatB = false) {
    loadChartContinuously0(chart, url, condition, 6 * 1000, formatB);
}

function loadChartContinuously0(chart, url, condition, interval = 6 * 1000, formatB = false) {
    chart.showLoading();
    obtainData(chart, url, condition, -1, -1, interval, formatB);
}

/**
 * 向后台请求数据
 */
function obtainData(chart, url, condition, startTime, endTime, interval = 6 * 1000, formatB = false) {
    if (startTime <= 0) {
        startTime = REQUEST_START_TIME;//12 * 60 * 60 * 1000;
    }

    let continuously = false;
    if (endTime <= 0) {
        endTime = new Date();
        continuously = true;
    }

    $.ajax({
        type: "post",
        contentType: "application/json",
        url: url,
        async: true,
        data: JSON.stringify({
            condition: condition,
            startTime: startTime,
            endTime: endTime
        }),
        dataType: "json",
        timeout: 2 * 60 * 1000,
        success: function (result) {
            if (result) {
                if ('undefined' == typeof chart.getOption()) {
                    init(chart, result, formatB)
                } else {
                    refresh(chart, result);
                }
            }
            if (continuously) {
                setTimeout(function () {
                    obtainData(chart, url, condition, endTime, -1, interval);
                }, interval);
            }
        },
        error: function (errorMsg) {
            console.log("图表请求数据失败:" + errorMsg.toString());
            setTimeout(function () {
                obtainData(chart, url, condition, startTime, endTime, interval, formatB);
            }, 3 * 60 * 1000); // 请求失败，3分钟后再重新尝试连接
        }
    });
}

/**
 * 刷新报表数据
 */
function refresh(chart, result) {
    let series = chart.getOption().series;

    for (let i = 0; i < series.length; i++) {
        for (let j = 0; j < result.series[i].data.length; j++) {
            series[i].data.shift();
            series[i].data.push(result.series[i].data[j]);
        }
    }

    chart.setOption({
        series: series
    })
}

/**
 * 初始化图表配置
 */
function init(chart, result, formatB = false) {
    let opt = option(formatB);

    opt.title.text = result.title;
    opt.series = result.series;
    opt.legend.data = result.legend;
    opt.legend.selected = result.legendUnSelected;

    chart.setOption(opt);

    chart.hideLoading();
}

/**
 * echarts 配置
 */
function option(formatB = false) {
    // 指定图表的配置项和数据
    return {
        tooltip: {
            // 当trigger为’item’时只会显示该点的数据，为’axis’时显示该列下所有坐标轴所对应的数据。
            trigger: 'axis',
            formatter: function (params) {
                let res = '<p>时间：' + new Date(params[0].axisValue * 1).format("yy-MM-dd HH:mm") + '</p>';
                for (let i = 0; i < params.length; i++) {
                    let size = formatB ? (params[i].data[1] * 1).formatSize() : (params[i].data[1] * 1);
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
                    return formatB ? (1 * value).formatSize() : value;
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
}