FacUse = {
    table: null
}

layui.use('table', function () {
    FacUse.table = layui.table;
    FacUse.table.render({
        elem: '#facUseList'
        , url: MyObject.ctxPath + '/facUse/getPageList'
        , toolbar: '#toolbar'
        , method: 'post'
        , title: '设备基础信息表'
        , page: true
        , where: {'facName':null}
        , limit: 10
        , limits: [10, 20, 30, 40, 50, 60, 70, 80, 90]
        , cols: [[
            {field: 'facName', title: '设施名'}
            , {field: 'facAddress', title: '库存地址'}
            , {field: 'useNumber', title: '使用数量'}
            , {field: 'name', title: '使用人'}
            , {field: 'useTime', title: '使用时间'}
            , {field: 'instructions', title: '使用说明'}
            , {title: '操作', toolbar: '#bar'}
        ]]
    });

    //表格头按钮监听
    FacUse.table.on('toolbar(facUseList)', function (obj) {
        switch (obj.event) {
            case 'add':
                layerOpenClose('新增设备使用信息', '/facUse/facUseInfo?id=', 800, 600, FacUse.refresh);
                break;
        };
    });

    //表格行按钮事件监听
    FacUse.table.on('tool(facUseList)', function (obj) {
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        switch (layEvent) {
            case 'edit':
                var id = obj.data.id;
                layerOpenClose('编辑设备使用信息', '/facUse/facUseInfo?id='+id, 800, 600, FacUse.refresh);
                break;
            case 'del':
                var operation = function () {
                    var ajax = new $ax(MyObject.ctxPath + "/facUse/delete", function (data) {
                        MyObject.success("删除成功！");
                        FacUse.refresh();
                    }, function (data) {
                        MyObject.error("删除失败!" + data.responseJSON.msg + "!");
                    })
                    ajax.set("id", obj.data.id);
                    ajax.start();
                }
                //询问是否删除
                MyObject.confirm("是否删除设施使用信息:" + obj.data.facId + "?", operation)
                break;
        };
    });
})

//搜索
FacUse.search = function() {
    var facName = $("#facName").val();
    FacUse.table.reload("facUseList",{
        url: MyObject.ctxPath + '/facUse/getPageList',
        page:{
            curr: 1
        },
        where:{
            'facName':facName
        }
    });

}

//刷新
FacUse.refresh = function() {
    var facName = $("#facName").val();
    FacUse.table.reload("facUseList",{
        url: MyObject.ctxPath + '/facUse/getPageList',
        where:{
            'facName':facName
        }
    });
}