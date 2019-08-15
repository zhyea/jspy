Date.prototype.format = function (fmt) {

    let o = {
        "M+": this.getMonth() + 1,                 //月份   
        "d+": this.getDate(),                    //日   
        "h+": this.getHours(),                   //小时   
        "H+": this.getHours(),                   //小时   
        "m+": this.getMinutes(),                 //分   
        "s+": this.getSeconds(),                 //秒   
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
        "S": this.getMilliseconds()             //毫秒   
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (let k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};


Number.prototype.formatStorage = function () {
    let sizes = ['B', 'K', 'M', 'G', 'T'];
    if (0 >= this) return 0;
    let i = parseInt(Math.floor(Math.log(this) / Math.log(1024)) + '');
    return (this / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
};


/**
 * 格式化存储数据
 */
function formatStorage(bytes) {
    let sizes = ['B', 'K', 'M', 'G', 'T'];
    if (bytes == 0) return 0;
    let i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)) + '');
    return (bytes / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
}

/**
 * 格式化长整型数字
 */
function formatLong(src) {
    return Math.round(src).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,')
}


/**
 * 格式化毫秒时间
 */
function formatTime(src) {
    return moment(src).format('YY/MM/DD HH:mm:ss')
}


/**
 * 复制到粘贴板
 */
function copyToClipboard(text) {
    let aux = document.createElement("input");
    aux.setAttribute("value", text);
    document.body.appendChild(aux);
    aux.select();
    document.execCommand("Copy");
    document.body.removeChild(aux);
}


/**
 * 以模拟表单的形式发送post请求
 */
function post(url, params, target) {

    target = target || '_blank';

    let tempForm = document.createElement("form");
    tempForm.action = url;
    tempForm.method = "post";
    tempForm.style.display = "none";

    if (target) {
        tempForm.target = target;
    }

    for (let x in params) {
        let opt = document.createElement("input");
        opt.name = x;
        opt.value = params[x];
        tempForm.appendChild(opt);
    }

    let opt = document.createElement("input");
    opt.type = "submit";
    tempForm.appendChild(opt);
    document.body.appendChild(tempForm);
    tempForm.submit();
    document.body.removeChild(tempForm);
}