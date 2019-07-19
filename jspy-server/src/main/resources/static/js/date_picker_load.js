/**
 * 加载时间选择控件
 */
function loadDateRangePicker(attachEle, callback) {

    attachEle.daterangepicker({
        endDate: moment(), //最大时间
        showDropdowns: true,
        showWeekNumbers: false, //是否显示第几周
        timePicker: true, //是否显示小时和分钟
        timePickerIncrement: 10, //时间的增量，单位为分钟
        timePicker12Hour: true, //是否使用12小时制来显示时间
        ranges: {
            '最近1小时': [moment().subtract('hours', 1), moment()],  //moment.js需要详细了解的可以点我一下
            '今天': [moment().startOf('day'), moment()],
            '昨天': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
            '最近7日': [moment().subtract('days', 6), moment()]
        },
        opens: 'right', //日期选择框的弹出位置
        buttonClasses: ['btn btn-default'],
        applyClass: 'btn-small btn-primary blue',
        cancelClass: 'btn-small',
        format: 'YY-MM-DD HH:mm', //控件中from和to 显示的日期格式
        separator: ' 至 ',
        locale: {
            applyLabel: '确定',
            cancelLabel: '取消',
            fromLabel: '起始时间',
            toLabel: '结束时间',
            customRangeLabel: '自定义时间',
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月',
                '六月', '七月', '八月', '九月', '十月', '十一月',
                '十二月'],
            firstDay: 1
        }
    }, callback);

}