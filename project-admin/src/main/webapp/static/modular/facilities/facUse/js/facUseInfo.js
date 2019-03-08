FacUseInfo = {
    stocknum:0,
    usenum:0,
    basefacstockId:""
}

layui.use(['form'], function () {
    var form = layui.form;
    form.on('submit(add)', function (data) {
        var ajax = new $ax(MyObject.ctxPath + "/facUse/addOrUpdate", function (data) {
            if(data.code == 200) {
                MyObject.success("提交成功！");
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
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
    var facstockId = $("#facstockIdVal").val();
    var userId = $("#userIdVal").val();
    $("#facstockId").val(facstockId);
    $("#userId").val(userId);
    form.render('select');

    //默认提示信息
    FacUseInfo.showMessage(facstockId);
    var useNumber = $("#useNumber").val();
    if(useNumber == "" || useNumber == null || useNumber == undefined) {
        FacUseInfo.usenum = 0;
    } else {
        FacUseInfo.usenum = useNumber;
        FacUseInfo.basefacstockId = facstockId;
    }

    //设施修改触发
    form.on('select(facstockId)', function (data) {
        FacUseInfo.showMessage(data.value);
    });

    //自定义使用数量验证
    form.verify({
        validateUseNum: function (value) {
            var result = FacUseInfo.validateUseNum(value);
            if (result != "Y") {
                return result;
            }
        }
    });
})

FacUseInfo.validateUseNum = function (num) {
    var nowfacstockId = $("#facstockId").val();
    if (FacUseInfo.basefacstockId == "" || FacUseInfo.basefacstockId == nowfacstockId) {
        //新增使用或未修改使用设备
        if (parseInt(num) <= parseInt(FacUseInfo.stocknum) + parseInt(FacUseInfo.usenum)) {
            return "Y";
        }
        return "剩余库存不足!";
    } else {
        //修改了使用设备，可使用库存不能大于当前库存量
        if (parseInt(num) <= parseInt(FacUseInfo.stocknum)) {
            return "Y";
        }
        return "剩余库存不足!";
    }
}

FacUseInfo.showMessage = function(id) {
    if (id == "" || id == null || id == undefined) {
        $("#showMessage").empty();
    } else {
        var ajax = new $ax(MyObject.ctxPath + "/facStock/getFacStockById", function (data) {
            var facNumer = data.facNumer;
            $("#showMessage").empty();
            if(facNumer == "" && facNumer == null && facNumer == undefined) {
                MyObject.error("获取库存信息异常!");
            } else {
                FacUseInfo.stocknum = facNumer;
                $("#showMessage").append("<span>该设施库存为：" + facNumer + "</span>");
            }
        }, function (data) {
            MyObject.error("获取库存信息异常!");
        });
        ajax.setData({"id":id});
        ajax.start();
    }
}
