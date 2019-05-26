Date.prototype.format = function (fmt) {

    var o = {
        "M+" : this.getMonth()+1,                 //月份   
        "d+" : this.getDate(),                    //日   
        "h+" : this.getHours(),                   //小时   
        "H+" : this.getHours(),                   //小时   
        "m+" : this.getMinutes(),                 //分   
        "s+" : this.getSeconds(),                 //秒   
        "q+" : Math.floor((this.getMonth()+3)/3), //季度   
        "S"  : this.getMilliseconds()             //毫秒   
    };
    if(/(y+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length));
    };
    for(var k in o){
        if(new RegExp("("+ k +")").test(fmt)){
            fmt=fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(o[k]):(("00"+ o[k]).substr((""+o[k]).length)));
        };
    }
    return fmt;
};

Number.prototype.formatSize = function () {
    var sizes = ['B','K','M','G','T'];
    if(0==this) return 0;
    var i = parseInt(Math.floor(Math.log(this) / Math.log(1024)));
    return (this / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
};

function formatSize(bytes){
    var sizes = ['B','K','M','G','T'];
    if (bytes == 0) return 0;
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return (bytes / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
};
