MonVacle = {
    table: null,
    form: null
};
layui.use(['table', 'form'], function () {
    MonVacle.form = layui.form;
    MonVacle.table = layui.table;
    MonVacle.tableInit();
    MonVacle.rowEvent();
});

MonVacle.tableInit = function () {
    MonVacle.table.render({
        elem: '#vcleList'
        , url: MyObject.ctxPath + '/vcleInfo/getVcleInfoList'
        , defaultToolbar: []
        , loading: true
        , method: 'post'
        , title: '车辆基本信息表'
        , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['prev', 'page', 'next']//自定义分页布局
            , first: false //不显示首页
            , last: false //不显示尾页
        }
        , limit: 5
        , size: 'sm'
        , skin: 'line'
        , limits: [10, 20, 30, 40, 50, 60]
        , cols: [[
             {field: 'plateNumber', title: '车牌号', width: '40%'}
            , {field: 'operationType', title: '车辆类型', width: '30%'}
            , {
                field: 'deadweight', title: '作业状态', width: '30%', templet: function () {
                    return '作业中'
                }
            }
        ]]
    })
};
MonVacle.rowEvent = function () {
    MonVacle.table.on('row(vcleList)', function (obj) {
        var trclass = obj.tr.selector;
        var objas = $(trclass)
        $(trclass + " input[name=\"layTableRadio_1\"]").siblings('div').addClass('layui-form-radioed');
        var data = obj.data;
        layer.alert(JSON.stringify(data), {
            title: '当前行数据：'
        });
        //标注选中样式
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
    });
};