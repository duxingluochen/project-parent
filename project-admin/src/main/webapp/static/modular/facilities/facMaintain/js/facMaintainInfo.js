FacMaintainInfo = {
}

layui.use(['form'], function () {
    var form = layui.form;
    form.on('submit(add)', function (data) {
        //价格逗号替换
        var tainPrice = data.field.tainPrice;
        data.field.tainPrice = tainPrice.replace(',','');
        //提交数据
        var ajax = new $ax(MyObject.ctxPath + "/facMaintain/addOrUpdate", function (data) {
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
    //是否可编辑
    var edit = $("#edit").val();
    if(edit != 'N') {
        $(".edit-btn").show();
    }

    //自定义金额验证
    form.verify({
        validateMoney: function (value) {
            var result = FacMaintainInfo.validateMoney(value);
            if (result != "Y") {
                return result;
            }
        },
        beforePhotos: function (value) {
            if (value == null || value == '') {
                return '请上传维护前照片';
            }
        },
        afterPhotos: function (value) {
            if (value == null || value == '') {
                return '请上传维护后照片';
            }
        }
    });
})

//头像上传
layui.use('upload', function(){
    var $ = layui.jquery,upload = layui.upload;
    //维护前照片
    var before = upload.render({
        elem: '#beforebtn'
        ,url: '/file/fileUpload'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#beforeimg').attr('src', result); //图片链接（base64）
            });
            layer.load(); //上传loading
            this.data={'type':1};
        }
        ,done: function(res){
            if(res.code == 200) {//上传成功
                var imgId = res.data;
                $("#beforePhotos").val(imgId);
                layer.msg('上传成功', { icon: 1 });
                $('#before').empty();
                layer.closeAll('loading');
            } else {//上传失败
                layer.msg('上传失败', { icon: 1 });
            }
        }
        ,error: function(){
            //失败状态，并实现重传
            var demoText = $('#before');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                before.upload();
            });
        }
    });

    //维护后照片
    var after = upload.render({
        elem: '#afterbtn'
        ,url: '/file/fileUpload'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#afterimg').attr('src', result); //图片链接（base64）
            });
            layer.load(); //上传loading
            this.data={'type':1};
        }
        ,done: function(res){
            if(res.code == 200) {//上传成功
                var imgId = res.data;
                $("#afterPhotos").val(imgId);
                layer.msg('上传成功', { icon: 1 });
                $('#after').empty();
                layer.closeAll('loading');
            } else {//上传失败
                layer.msg('上传失败', { icon: 1 });
            }
        }
        ,error: function(){
            //失败状态，并实现重传
            var demoText = $('#after');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                after.upload();
            });
        }
    });
})

FacMaintainInfo.validateMoney = function (money) {
    var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    if (reg.test(money)) {
        return "Y";
    }
    return "请输入正确的金额,且最多两位小数!";
}
