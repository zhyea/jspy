/**
 * 加载时间选择控件
 */
function loadDateRangePicker(attachEle, callback) {

    attachEle.daterangepicker({
        startDate: moment().subtract('hours', 1),
        endDate: moment(), //最大时间
        showDropdowns: true,
        showWeekNumbers: false, //是否显示第几周
        timePicker: true, //是否显示小时和分钟
        timePickerIncrement: 10, //时间的增量，单位为分钟
        timePicker12Hour: true, //是否使用12小时制来显示时间
        ranges: {
            '最近1小时': ['hours', 1],
            '最近12小时': ['hours', 12],
            '最近24小时': ['hours', 24],
            '最近3日': ['days', 3]
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