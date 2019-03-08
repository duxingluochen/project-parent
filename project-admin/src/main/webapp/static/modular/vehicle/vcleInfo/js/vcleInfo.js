VcleInfo = {
    table: null,
    laydate: null
};
layui.use(['table', 'laydate'], function () {
    VcleInfo.table = layui.table;
    VcleInfo.laydate = layui.laydate;
    VcleInfo.vcleInfoInit();
    VcleInfo.toolbar();
    VcleInfo.vcleInfobar();
    VcleInfo.getVcleInfoProportionlist();
});
//初始化表格
VcleInfo.vcleInfoInit = function () {
    VcleInfo.table.render({
        elem: '#vcleInfoList'
        , url: MyObject.ctxPath + '/vcleInfo/getVcleInfoList'
        , toolbar: '#toolbarVcleInfo'
        , method: 'post'
        , title: '车辆基本信息表'
        , page: true
        , limit: 10
        , limits: [10, 20, 30, 40, 50, 60]
        , cols: [[
            {field: 'vcleName', title: '车辆名称'}
            , {field: 'plateNumber', title: '车牌号'}
            , {field: 'startUsingTime', title: '开始使用时间'}
            , {field: 'deadweight', title: '载重（吨）'}
            , {field: 'operationType', title: '作业类型'}
            , {title: '操作', toolbar: '#vcleInfobar'}
        ]]
    })
};
//工具栏监听事件
VcleInfo.toolbar = function () {
    VcleInfo.table.on("toolbar(vcleInfoList)", function (obj) {
        switch (obj.event) {
            case 'vcleInfo_add':
                layerOpenClose("添加车辆", MyObject.ctxPath + "/vcleInfo/add", 1090, 550, VcleInfo.refresh);
                break;
            case 'refresh':
                VcleInfo.refresh();
                break;
        }
    })
};
//表格重载
VcleInfo.refresh = function () {
    var vcleName = $("#vcleName").val();
    var plateNumber = $("#plateNumber").val();
    VcleInfo.table.reload('vcleInfoList', {
        where: {
            vcleName: vcleName,
            plateNumber: plateNumber
        }
    })
};
VcleInfo.vcleInfobar = function () {
    VcleInfo.table.on('tool(vcleInfoList)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        if (layEvent === 'edit') {
            layerOpenClose("车辆信息查看", MyObject.ctxPath + "/vcleInfo/edit?vcleId=" + data.id, 1090, 750, VcleInfo.refresh);
        } else if (layEvent === 'del') {
            var operation = function () {
                var ajax = new $ax(MyObject.ctxPath + "/vcleInfo/delete", function (data) {
                    MyObject.success("删除成功！");
                    VcleInfo.refresh();
                }, function (data) {
                    MyObject.error("删除失败!" + data.responseJSON.msg + "!");
                });
                ajax.set("id", data.id);
                ajax.start();
            };
            //询问是否删除
            MyObject.confirm("是否删除车辆：" + data.plateNumber, operation)
        }
    })
};

VcleInfo.getVcleInfoProportionlist = function () {
    //layer.load();
    var ajax = new $ax(MyObject.ctxPath + "/vcleInfo/getVcleInfoProportionlist", function (data) {
        VcleInfo.getVcleTypeProp(VcleInfo.process(data));
        VcleInfo.getVcleNameProp(VcleInfo.process(data))
    }, function (data) {
        //layer.closeAll("loading");
        MyObject.error("数据获取异常!");
    });
    ajax.start();
};
VcleInfo.getVcleTypeProp = function (data) {
    var myChart = echarts.init(document.getElementById('typeChart'));
    myChart.setOption({
        title: {
            text: '车辆类型占比',
            left: 'left'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
            {
                name: '车辆类型占比',
                type: 'pie',
                radius: '52%',
                center: ['35%', '55%'],
                data: data.typeValue,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    });
};
VcleInfo.getVcleNameProp = function (data) {
    var myChart = echarts.init(document.getElementById('nameChart'));
    myChart.setOption({
        title: {
            text: '车辆名称占比',
            left: 'left'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
            {
                name: '部门',
                type: 'pie',
                radius: '52%',
                center: ['35%', '58%'],
                data: data.nameValue,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    });
};

VcleInfo.process = function (data) {
    var typeValue = [];
    var nameValue = [];
    var totalNum = 0;
    $.each(data[0], function (index, item) {
        typeValue.push({value: item.value, name: item.name});
        totalNum += item.value;
    });
    $.each(data[1], function (index, item) {
        nameValue.push({value: item.value, name: item.name});
    });
    $("#totalNum").html(totalNum);
    return {
        nameValue: nameValue,
        typeValue: typeValue
    }
};


