FacMaintain = {
    table: null
}

layui.use('table', function () {
    FacMaintain.table = layui.table;
    FacMaintain.table.render({
        elem: '#facMaintainList'
        , url: MyObject.ctxPath + '/facMaintain/getFacMaintainList'
        , toolbar: '#toolbar'
        , method: 'post'
        , title: '设备维护表'
        , page: true
        , limit: 10
        , where: {'facName':null,'selection':'all'}
        , limits: [10, 20, 30, 40, 50, 60, 70, 80, 90]
        , cols: [[
            {field: 'facId', title: '设施名'}
            , {field: 'tainContent', title: '维护内容'}
            , {field: 'tainPrice', title: '维护价格'}
            , {field: 'createTime', title: '填报时间'}
            , {title: '进度', toolbar: '#flowbar', width : 350}
            , {title: '操作', toolbar: '#bar'}
        ]]
    });

    //表格头按钮监听
    FacMaintain.table.on('toolbar(facMaintainList)', function (obj) {
        switch (obj.event) {
            case 'add':
                layerOpenClose('发起设备维护申请', '/facMaintain/facMaintainInfo?id=', 800, 600, FacMaintain.refresh);
                break;
        };
    });

    //表格行按钮事件监听
    FacMaintain.table.on('tool(facMaintainList)', function (obj) {
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        switch (layEvent) {
            case 'edit':
                var id = obj.data.id;
                layerOpenClose('设备维护申请修改', '/facMaintain/facMaintainInfo?id='+id, 800, 600, FacMaintain.refresh);
                break;
            case 'del':
                var operation = function () {
                    var ajax = new $ax(MyObject.ctxPath + "/facMaintain/delete", function (data) {
                        if(data.code == 200) {
                            MyObject.success("撤销成功！");
                            FacMaintain.refresh();
                        } else {
                            MyObject.error(data.msg + "，不能撤销！");
                        }
                    }, function (data) {
                        MyObject.error("撤销失败!");
                    })
                    ajax.set("id", obj.data.id);
                    ajax.start();
                }
                //询问是否删除
                MyObject.confirm("是否撤销并删除该申请:" + obj.data.facId + "?", operation);
                break;
            case 'approve':
            case 'details':
                var id = obj.data.id;
                var flowId = obj.data.afterPhotos;
                layerOpenClose('设备维护申请审核', '/facMaintain/facMaintainApproval?id='+id+'&flowId='+flowId, 800, 600, FacMaintain.refresh);
                break;
        };
    });
})

//任务筛选-改变事件
layui.use(['form'], function () {
    var form = layui.form;
    form.on('radio(selection)', function (data) {
        FacMaintain.search();
    });
})

//搜索
FacMaintain.search = function() {
    var selection = $('input:radio[name="selection"]:checked').val();
    var facName = $("#facName").val();
    FacMaintain.table.reload("facMaintainList",{
        url: MyObject.ctxPath + '/facMaintain/getFacMaintainList',
        page:{
            curr: 1
        },
        where:{
            'facName':facName,
            'selection':selection
        }
    });
}

//刷新
FacMaintain.refresh = function() {
    var selection = $('input:radio[name="selection"]:checked').val();
    var facName = $("#facName").val();
    FacMaintain.table.reload("facMaintainList",{
        url: MyObject.ctxPath + '/facMaintain/getFacMaintainList',
        where:{
            'facName':facName,
            'selection':selection
        }
    });
}