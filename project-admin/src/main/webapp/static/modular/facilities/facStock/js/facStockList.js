FacStock = {
    table: null
}

layui.use('table', function () {
    FacStock.table = layui.table;
    FacStock.table.render({
        elem: '#facStockList'
        , url: MyObject.ctxPath + '/facStock/getFacStockList'
        , toolbar: '#toolbar'
        , method: 'post'
        , title: '设备维护表'
        , page: true
        , limit: 10
        , where: {'facName':null,'facType':null}
        , limits: [10, 20, 30, 40, 50, 60, 70, 80, 90]
        , cols: [[
            {field: 'facId', title: '设施名'}
            , {field: 'facNumer', title: '设施库存数量'}
            , {field: 'facType', title: '设施类型'}
            , {field: 'facAddress', title: '存放地点'}
            , {field: 'facManager', title: '设施管理人员'}
            , {title: '操作', toolbar: '#bar'}
        ]]
    });

    //表格头按钮监听
    FacStock.table.on('toolbar(facStockList)', function (obj) {
        switch (obj.event) {
            case 'add':
                layerOpenClose('新增设备库存信息', '/facStock/facStockInfo?id=', 800, 600, FacStock.refresh);
                break;
        };
    });

    //表格行按钮事件监听
    FacStock.table.on('tool(facStockList)', function (obj) {
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        switch (layEvent) {
            case 'edit':
                var id = obj.data.id;
                layerOpenClose('编辑设备库存信息', '/facStock/facStockInfo?id='+id, 800, 600, FacStock.refresh);
                break;
            case 'del':
                var operation = function () {
                    var ajax = new $ax(MyObject.ctxPath + "/facStock/delete", function (data) {
                        if(data.code == 200) {
                            MyObject.success("删除成功！");
                            FacStock.refresh();
                        } else if (data.code == 1100) {
                            MyObject.error(data.msg);
                        } else {
                            MyObject.error("删除失败!");
                        }
                    }, function (data) {
                        MyObject.error("删除失败!");
                    })
                    ajax.set("id", obj.data.id);
                    ajax.start();
                }
                //询问是否删除
                MyObject.confirm("是否删除设施库存信息:" + obj.data.facId + "/" + obj.data.facAddress + "?", operation)
                break;
        };
    });
})

//搜索
FacStock.search = function() {
    var facName = $("#facName").val();
    var facType = $("#facType").val();
    FacStock.table.reload("facStockList",{
        url: MyObject.ctxPath + '/facStock/getFacStockList',
        page:{
            curr: 1
        },
        where:{
            'facName':facName,
            'facType':facType
        }
    });
}

//刷新
FacStock.refresh = function() {
    var facName = $("#facName").val();
    FacStock.table.reload("facStockList",{
        url: MyObject.ctxPath + '/facStock/getFacStockList',
        where:{
            'facName':facName
        }
    });
}