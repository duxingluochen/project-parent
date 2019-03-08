VcleInfo = {
    form: null,
    laydate: null,
    upload: null,
    uploadInst: null
};
layui.use(['form', 'laydate', 'upload'], function () {
    VcleInfo.form = layui.form;
    VcleInfo.laydate = layui.laydate;
    VcleInfo.upload = layui.upload;
    VcleInfo.getVcleInfo();
    VcleInfo.updateVcleInfo();
    VcleInfo.loadDate();
    VcleInfo.uploadPic();
    VcleInfo.selectDriver();
    VcleInfo.showOilChart();
});
$(function () {
    //部门选择值
    var ztree = new $ZTree("tree", "/dept/tree");
    ztree.bindOnClick(VcleInfo.onClickDept);
    ztree.init();
    instance = ztree;
    $("body").bind("mousedown", VcleInfo.hideSelect);
});
//添加提交
VcleInfo.updateVcleInfo = function () {
    VcleInfo.form.on('submit(updateVcleInfo)', function (data) {
        var ajax = new $ax(MyObject.ctxPath + "/vcleInfo/update", function (data) {
            MyObject.success("保存成功!");
        }, function (data) {
            MyObject.error("保存失败!");
        });
        ajax.setData(data.field);
        ajax.start();
    });
};
//上传车辆照片
VcleInfo.uploadPic = function () {
    VcleInfo.demoText = $('#demoText');
    VcleInfo.uploadInst = VcleInfo.upload.render({
        elem: '#uploadPic'
        , url: MyObject.ctxPath + '/file/fileUpload'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#vclePic').attr('src', result); //图片链接（base64）
            });
            layer.load(); //上传loading
            this.data = {'type': 2};
        }
        , done: function (res) {
            //如果上传失败
            if (res.msg == "success") {
                //上传成功
                VcleInfo.demoText.html('<span style="color: green;">上传成功</span>');
                $("#picture").val(res.data);
                layer.closeAll('loading');
            }
            else {
                VcleInfo.demoText.html('<span style="color: #FF5722;">上传失败</span>');
            }

        }
        , error: function () {
            //演示失败状态，并实现重传
            VcleInfo.demoText.html('<span style="color: #FF5722;">上传失败网络繁忙</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            VcleInfo.demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });
};
//时间选择器
VcleInfo.loadDate = function () {
    VcleInfo.laydate.render({
        elem: '#startUsingTime'
    });
    VcleInfo.laydate.render({
        elem: '#lastInspectionTime'
    });
};
//司机选择
VcleInfo.selectDriver = function () {
    VcleInfo.form.on('select(hc_select)', function (data) {
        //选择移交单位 赋值给input框
        $("#driverName").val(data.elem[data.elem.selectedIndex].text);
        $("#driverId").val(data.value);
        $("#hc_select").next().find("dl").css({"display": "none"});
        VcleInfo.form.render();
    });
};
//弹出下拉框
VcleInfo.search = function () {
    var value = $("#driverName").val();
    $("#hc_select").val(value);
    VcleInfo.form.render();
    $("#hc_select").next().find("dl").css({"display": "block"});
    var dl = $("#hc_select").next().find("dl").children();
    var j = -1;
    for (var i = 0; i < dl.length; i++) {
        if (dl[i].innerHTML.indexOf(value) <= -1) {
            dl[i].style.display = "none";
            j++;
        }
        if (j == dl.length - 1) {
            $("#hc_select").next().find("dl").css({"display": "none"});
        }
    }
};
//获取焦点显示下拉框
VcleInfo.show = function () {
    $("#hc_select").next().find("dl").css({"display": "block"});
};


//点击赋值
VcleInfo.onClickDept = function (e, treeId, treeNode) {
    $("#deptSel").val(instance.getSelectedVal());
    $("#department").val(treeNode.id);
    VcleInfo.form.render();
};
/**
 * 显示部门树
 */
VcleInfo.showDeptSelectTree = function () {
    $("#menuContent").css("display", "block");
};
/**
 * 隐藏弹出框
 */
VcleInfo.hideSelect = function () {
    $("#menuContent").fadeOut("fast");
    $("#hc_select").next().find("dl").fadeOut("fast");
};
/**
 * 获取车辆基本信息
 */
VcleInfo.getVcleInfo = function () {
    layer.load();
    var ajax = new $ax(MyObject.ctxPath + '/vcleInfo/getVcleInfo', function (data) {
            if (data.msg == "success") {
                for (item in data.vcleInfo) {
                    $("#" + item).val(data.vcleInfo[item]);
                }
                VcleInfo.form.render();
                $("#deptSel").val(data.deptName);
                $("#driverName").val(data.list[0].driName);
                $("#driverId").val(data.list[0].dri_id);
                $("#vclePic").attr("src", httpPath + data.picPath);

            }
            layer.closeAll("loading");
        },
        function () {
        });
    ajax.set('vcleId', vcleId);
    ajax.start();
};
/**
 * 油量水量统计图
 */
VcleInfo.showOilChart = function () {
    var myChart = echarts.init(document.getElementById('oilChart'));
    option = {
        title: {
            text: '车辆水量油量'
        },
        tooltip: {
            trigger: 'axis',
            formatter: function(params){
                var res="";
                res+=params[0].axisValue+":" ;
                res += '<br/>' + params[0].seriesName + ' : ' + params[0].value+"L";
                res += '<br/>' + params[1].seriesName + ' : ' + params[1].value+"吨";
                return res;
            }
        },
        legend: {
            data: ['油量消耗', '水量消耗']
        },
        toolbox: {
            show: true,
            feature: {
                magicType: {type: ['line', 'bar']},
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: [
            {
                type: 'value',
                position: 'left',
                axisLabel: {
                    formatter: '{value} L'
                }
            }, {
                type: 'value',
                position: 'left',
                offset: 50,
                axisLabel: {
                    formatter: '{value} 吨'
                }
            }
        ],
        series: [
            {
                name: '油量消耗',
                type: 'line',
                yAxisIndex: 1,
                data: [11, 11, 15, 13, 12, 13, 10],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'},
                        [{
                            symbol: 'none',
                            x: '90%',
                            yAxis: 'max'
                        }, {
                            symbol: 'circle',
                            label: {
                                normal: {
                                    position: 'start',
                                    formatter: '最大值'
                                }
                            },
                            type: 'max',
                            name: '最高点'
                        }]
                    ]
                }
            },
            {
                name: '水量消耗',
                type: 'line',
                data: [10, 11, 12, 11, 15, 16, 11],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'},
                        {
                            symbol: 'none',
                            x: '90%',
                            yAxis: 'max'
                        }
                    ]
                }
            }
        ]
    };
    myChart.setOption(option);
};









