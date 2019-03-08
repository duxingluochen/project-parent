FacilitiesInfo = {}

layui.use(['form'], function () {
    var form = layui.form;
    form.on('submit(add)', function (data) {
        var ajax = new $ax(MyObject.ctxPath + "/facinfo/addOrUpdate", function (data) {
            if(data.code == 200) {
                MyObject.success("提交成功！");
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            } else {
                MyObject.success("提交失败!");
            }
        }, function (data) {
            MyObject.error("提交失败!" + data.responseJSON.msg + "!");
        });
        ajax.setData(data.field);
        ajax.start();
        return false;
    })

    //自定义金额验证
    form.verify({
        validateMoney: function (value) {
            var result = FacilitiesInfo.validateMoney(value);
            if (result != "Y") {
                return result;
            }
        }
    });
})

FacilitiesInfo.validateMoney = function (money) {
    var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    if (reg.test(money)) {
        return "Y";
    }
    return "请输入正确的金额,且最多两位小数!";
}