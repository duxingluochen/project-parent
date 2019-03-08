FacMaintainApproval = {
}

$(function () {
    //是否可编辑
    var edit = $("#edit").val();
    if(edit != 'N') {
        $("#submit_button").show();
    }
})

layui.use(['form'], function () {
    //表单提交事件
    var form = layui.form;
    form.on('submit(add)', function (data) {
        var ajax = new $ax(MyObject.ctxPath + "/facMaintain/saveApproval", function (data) {
            if(data.code == 200) {
                MyObject.success("提交成功！");
            } else {
                MyObject.error("提交失败!");
            }
        }, function (data) {
            MyObject.error("提交失败!" + data.responseJSON.msg + "!");
        });
        ajax.setData(data.field);
        ajax.start();
        return false;
    })

    //下拉赋值
    var facId = $("#facIdVal").val();
    $("#facId").val(facId);
    form.render('select');
})
