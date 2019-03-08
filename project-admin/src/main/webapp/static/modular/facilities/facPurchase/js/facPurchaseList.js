FacPurchase = {
    table: null
}

layui.use('table', function () {
    FacPurchase.table = layui.table;
    FacPurchase.table.render({
        elem: '#facPurchaseList'
        , url: MyObject.ctxPath + '/facPurchase/getFacPurchaseList'
        , method: 'post'
        , title: '设备维护表'
        , page: true
        , limit: 10
        , where: {'facName':null}
        , limits: [10, 20, 30, 40, 50, 60, 70, 80, 90]
        , cols: [[
            {field: 'facId', title: '设施名'}
            , {field: 'purNumber', title: '采购数量'}
            , {field: 'purPrice', title: '采购单价'}
            , {field: 'purTotalPrice', title: '采购总价'}
            , {field: 'remark', title: '备注'}
            , {field: 'createTime', title: '填报时间'}
        ]]
    });
})

//搜索
FacPurchase.search = function() {
    var facName = $("#facName").val();
    FacPurchase.table.reload("facPurchaseList",{
        url: MyObject.ctxPath + '/facPurchase/getFacPurchaseList',
        page:{
            curr: 1
        },
        where:{
            'facName':facName
        }
    });
}