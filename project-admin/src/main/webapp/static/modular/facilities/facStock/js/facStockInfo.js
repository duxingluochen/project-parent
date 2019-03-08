FacStockInfo = {
    stocknum:0,
    usenum:0,
    basefacstockId:""
}

layui.use(['form'], function () {
    var form = layui.form;
    form.on('submit(add)', function (data) {
        var ajax = new $ax(MyObject.ctxPath + "/facStock/addOrUpdate", function (data) {
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
    var facManager = $("#facManagerVal").val();
    $("#facId").val(facId);
    $("#facManager").val(facManager);
    form.render('select');
})
