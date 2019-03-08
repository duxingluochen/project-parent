Facilities = {
    table: null
}

layui.use('table', function () {
    Facilities.table = layui.table;
    Facilities.table.render({
        elem: '#facilitiesList'
        , url: MyObject.ctxPath + '/facinfo/getFacPageList'
        , toolbar: '#toolbarFac'
        , method: 'post'
        , title: '设备基础信息表'
        , page: true
        , limit: 10
        , limits: [10, 20, 30, 40, 50, 60, 70, 80, 90]
        , cols: [[
            {field: 'facName', title: '设施名'}
            , {field: 'facType', title: '设施类型'}
            , {field: 'facPurpose', title: '设施用途'}
            , {field: 'facPrice', title: '设施单价'}
            , {field: 'facBrand', title: '设施品牌'}
            , {title: '操作', toolbar: '#facbar'}
        ]]
    });

    //表格头按钮监听
    Facilities.table.on('toolbar(facilitiesList)', function (obj) {
        switch (obj.event) {
            case 'add':
                layerOpenClose('新增设备', '/facinfo/facilitiesInfo?id=', 800, 600, Facilities.refresh);
                break;
        };
    });

    //表格行按钮事件监听
    Facilities.table.on('tool(facilitiesList)', function (obj) {
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        switch (layEvent) {
            case 'edit':
                var id = obj.data.id;
                layerOpenClose('编辑设备', '/facinfo/facilitiesInfo?id='+id, 800, 600, Facilities.refresh);
                break;
            case 'del':
                var operation = function () {
                    var ajax = new $ax(MyObject.ctxPath + "/facinfo/delete", function (data) {
                        MyObject.success("删除成功！");
                        Facilities.refresh();
                    }, function (data) {
                        MyObject.error("删除失败!" + data.responseJSON.msg + "!");
                    })
                    ajax.set("id", obj.data.id);
                    ajax.start();
                }
                //询问是否删除
                MyObject.confirm("是否删除设施:" + obj.data.facName + "?", operation)
                break;
        };
    });
})

//搜索
Facilities.search = function() {
    var facName = $("#facName").val();
    var facType = $("#facType").val();
    Facilities.table.reload("facilitiesList",{
        url: MyObject.ctxPath + '/facinfo/getFacPageList',
        where:{
            'facName':facName,
            'facType':facType
        }
    });

}

//刷新
Facilities.refresh = function() {
    var facName = $("#facName").val();
    var facType = $("#facType").val();
    Facilities.table.reload("facilitiesList",{
        url: MyObject.ctxPath + '/facinfo/getFacPageList',
        where:{
            'facName':facName,
            'facType':facType
        }
    });
}