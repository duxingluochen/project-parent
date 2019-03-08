SanAchievement = {
    table: null,
    form: null,
    laydate: null
};
layui.use(['table', 'form', 'laydate'], function () {
    SanAchievement.table = layui.table;
    SanAchievement.form = layui.form;
    SanAchievement.laydate = layui.laydate;
    SanAchievement.tableInit();
    SanAchievement.dateInit();
    SanAchievement.cvaInit();
    SanAchievement.rowEvent();
});
SanAchievement.tableInit = function () {
    SanAchievement.table.render({
        elem: '#userTableList'
        , url: MyObject.ctxPath + '/user/getUserList'
        , method: 'post'
        , title: '人员信息表'
        , page: true
        , limit: 15
        , skin: 'line'
        , limits: [15, 30]
        , cols: [[
            {field: 'name', title: '名称'}
            , {field: 'roleName', title: '职位'}
        ]]
    })
};
SanAchievement.dateInit = function () {
    SanAchievement.laydate.render({
        elem: '#start',
        range: true
    });
};
SanAchievement.rowEvent = function () {
//监听行单击事件（单击事件为：rowDouble）
    SanAchievement.table.on('row(userTableList)', function (obj) {
        var data = obj.data;
        layer.alert(JSON.stringify(data), {
            title: '当前行数据：'
        });
        //标注选中样式
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
    });
};
//工作评分统计
SanAchievement.cvaInit = function () {
    var cva = echarts.init(document.getElementById('cva'));
    option = {
        backgroundColor: '#ffffff',
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        legend: {
            data: ['工作评分'],
            textStyle: {
                color: '#505050'
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            boundaryGap: false,
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisLine: {
                lineStyle: {
                    color: '#505050',
                }
            }
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                formatter: '{value}分'
            },
            axisLine: {
                lineStyle: {
                    color: '#505050',
                }
            }
        }],
        series: [{
            name: '工作评分',
            type: 'line',
            stack: '总量',
            areaStyle: {},
            smooth: true,
            data: [81, 88, 77, 88, 77, 76, 85, 93, 97, 100, 91, 82],
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            {offset: 0, color: '#50d011'},
                            {offset: 0.6, color: '#b5dda1'},
                            {offset: 1, color: '#eaf9e3'}
                        ]
                    )
                }
            }
        }]
    };
    cva.setOption(option);
};
