VcleInfo = {
    form: null,
    laydate: null,
    upload: null,
    uploadInst: null
};
layui.use(['form', 'laydate', 'upload'], function () {
    VcleInfo.form = layui.form;
    VcleInfo.laydate = layui.laydate;
    VcleInfo.upload = layui.upload;
    VcleInfo.addVcleInfo();
    VcleInfo.loadDate();
    VcleInfo.uploadPic();
    VcleInfo.selectDriver();
});

//添加提交
VcleInfo.addVcleInfo = function () {
    VcleInfo.form.on('submit(addVcleInfo)', function (data) {
        layer.load();
        var ajax = new $ax(MyObject.ctxPath + "/vcleInfo/insert", function (data) {
            MyObject.success("添加成功!");
            layer.closeAll("loading");
        }, function (data) {
            MyObject.error("添加失败!");
            layer.closeAll("loading");
        });
        ajax.setData(data.field);
        ajax.start();
    });
};
//上传车辆照片
VcleInfo.uploadPic = function () {
    VcleInfo.demoText = $('#demoText');
    VcleInfo.uploadInst = VcleInfo.upload.render({
        elem: '#uploadPic'
        , url: MyObject.ctxPath + '/file/fileUpload'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#vclePic').attr('src', result); //图片链接（base64）
            });
            layer.load(); //上传loading
            this.data = {'type': 2};
        }
        , done: function (res) {
            //如果上传失败
            if (res.msg == "success") {
                //上传成功
                VcleInfo.demoText.html('<span style="color: green;">上传成功</span>');
                $("#picture").val(res.data);
                layer.closeAll('loading');
            }
            else {
                VcleInfo.demoText.html('<span style="color: #FF5722;">上传失败</span>');
            }

        }
        , error: function () {
            layer.closeAll("loading");
            //演示失败状态，并实现重传
            VcleInfo.demoText.html('<span style="color: #FF5722;">上传失败网络繁忙</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            VcleInfo.demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });
};
//时间选择器
VcleInfo.loadDate = function () {
    VcleInfo.laydate.render({
        elem: '#startUsingTime'
    });
    VcleInfo.laydate.render({
        elem: '#lastInspectionTime'
    });
};
//司机选择
VcleInfo.selectDriver = function () {
    VcleInfo.form.on('select(hc_select)', function (data) {
        //选择移交单位 赋值给input框
        $("#driverName").val(data.elem[data.elem.selectedIndex].text);
        $("#driverId").val(data.value);
        $("#hc_select").next().find("dl").css({ "display": "none" });
        VcleInfo.form.render();
    });
};
//弹出下拉框
VcleInfo.search = function () {
    var value = $("#driverName").val();
    $("#hc_select").val(value);
    VcleInfo.form.render();
    $("#hc_select").next().find("dl").css({"display": "block"});
    var dl = $("#hc_select").next().find("dl").children();
    var j = -1;
    for (var i = 0; i < dl.length; i++) {
        if (dl[i].innerHTML.indexOf(value) <= -1) {
            dl[i].style.display = "none";
            j++;
        }
        if (j == dl.length - 1) {
            $("#hc_select").next().find("dl").css({"display": "none"});
        }
    }
};
//获取焦点显示下拉框
VcleInfo.show=function () {
    $("#hc_select").next().find("dl").css({"display": "block"});
};
$(function () {
    //部门选择值
    var ztree = new $ZTree("tree", "/dept/tree");
    ztree.bindOnClick(VcleInfo.onClickDept);
    ztree.init();
    instance = ztree;
    $("body").bind("mousedown", VcleInfo.hideSelect);
});

VcleInfo.onClickDept = function (e, treeId, treeNode) {
    $("#deptSel").attr("value", instance.getSelectedVal());
    $("#department").attr("value", treeNode.id);
};
/**
 * 显示部门树
 */
VcleInfo.showDeptSelectTree = function () {
    $("#menuContent").css("display", "block");
};
/**
 * 隐藏弹出框
 */
VcleInfo.hideSelect=function () {
    $("#menuContent").fadeOut("fast");
    $("#hc_select").next().find("dl").fadeOut("fast");
};







