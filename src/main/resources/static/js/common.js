/**
 * ajax post请求
 * @param url
 * @param data
 * @param callback
 * @param async
 */
function ajaxPost(url, data, callback, async) {
    $.ajax({
        type: "post",
        url: url,
        data: data,
        dataType: "json",
        success: function (res) {
            callback(res);
        },
        async: async == true
    });
}

/**
 * layui提交监听
 * @param filter lay-filter过滤器值
 * @param callback 提交回调
 * @param prevent 阻止提交
 */
function onSubmit(filter, callback, prevent) {
    layui.use("form", function () {
        var form = layui.form;
        form.on("submit(" + filter + ")", function (data) {
            callback(data);
            return !(prevent == true);
        });
    });
}

function openWin(title, url, callback, width, height) {
    layui.use("layer", function () {
        var layer = layui.layer;

        //窗口大小
        var area = [];
        if (typeof(width) != undefined) {
            area[0] = width;
        }
        if (typeof(height) != undefined) {
            area[1] = height;
        }

        //自动大小函数
        var autoSize = function (layero) {
            var id = layero[0].id;
            var index = id.substring("layui-layer".length);
            var h = layero[0].style.height.substring(0, layero[0].style.height.indexOf("px")) - 70;
            var w = layero[0].style.width.substring(0, layero[0].style.width.indexOf("px"));
            $("#layui-layer-iframe" + index)[0].style.height = h + "px";
            $("#layui-layer-iframe" + index)[0].style.width = w + "px";
        }

        layer.open({
            type: 2,
            title: title,
            content: [url, "auto"],
            maxmin: true,
            scrollbar: false,
            area: area.length == 0 ? null : area,
            resizing : autoSize,
            full: autoSize,
            restore: autoSize,
            end: function (index, layero) {
                if (typeof(callback) == "function") {
                    callback(index, layero);
                }
            }
        });
    });
}

function closeWin(callback) {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index, function () {
        if (typeof(callback) == "function") {
            callback();
        }
    });
}

function initTable(options) {
    layui.use("table", function () {
        var table = layui.table;
        options.parseData = function (res) {
            return {
                "code": res.code,
                "msg": res.msg,
                "count": res.data != null ? res.data.length : 0,
                "data": res.data
            }
        }
        table.render(options);
    });
}

function tableReload(id, options, deep) {
    layui.use("table", function () {
        var table = layui.table;
        table.reload(id, options, deep);
    });
}

function initSwitch(filter, callback) {
    layui.use("form", function () {
        var form = layui.form;
        form.on("switch(" + filter + ")", function (data) {
            callback(data);
        });
    });
}

function getUrlParams() {
    var search = location.search;
    if (search == "") return null;
    var urlParams = {};
    var urlParamsStr = search.substring(1);
    urlParamsStr.split("&").forEach(function (item, index) {
        var eqIndex = item.indexOf('=');
        urlParams[item.substring(0, eqIndex)] = item.substring(eqIndex + 1)
    });
    return urlParams;
}