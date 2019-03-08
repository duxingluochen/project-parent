$(function() {
    //当前时间
    setInterval(function() {
        var date = new Date();

        var yy = date.getFullYear(); //年
        var MM = date.getMonth() + 1; //月
        var dd = date.getDate(); //日
        var hh = date.getHours(); //时
        var mm = date.getMinutes(); //分
        var ss = date.getSeconds(); //秒
        var week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[date.getDay()]; //星期

        if (hh < 10) {
            hh = "0" + hh;
        }
        if (mm < 10) {
            mm = "0" + mm;
        }
        if (ss < 10) {
            ss = "0" + ss;
        }

        $("#time1").html(yy + "年" + MM + "月" + dd + "日")
        $("#time2").html(week + "&nbsp; " + hh + ":" + mm + ":" + ss);

    }, 1000);

    //时间下方的方格动画
    var index;

    function setTab(name, cursel, n) {
        for (var i = 0; i < n; i++) {
            var menu = document.getElementById(name + i);
            menu.className = i == cursel ? "hover" : "";
            if (menu.className == "hover")
                $(menu).attr("src", rootPath + "static/modular/bigScreen/image/head-icon2.png").siblings().attr("src", rootPath + "static/modular/bigScreen/image/head-icon.png");
            index = i;
        }
    }
    var num = 0;
    var AutoPlayObj = null;

    function auto() {
        setTab('one', num % 4, 4);
        setTab('two', num % 4, 4);
        num++;
    }

    AutoPlay();

    function AutoPlay() {
        clearInterval(AutoPlayObj);
        AutoPlayObj = setInterval(auto, 700);
    };

    var tab = document.getElementById("carl");
    tab.onmouseover = function() {
        num = index;
        clearInterval(AutoPlayObj);
    }
    tab.onmouseout = function() {
        AutoPlay();
    }

    //环卫点总数数字滚动动画
    numRunFun(0, $("#number").text());

    function numRunFun(num, maxNum) {
        var numBox = document.getElementById("number");
        var numText = num;
        var golb; //清除requestAnimationFrame
        function numSlideFun() {
            numText += 678; //步进
            if (numText >= maxNum) {
                numText = maxNum;
                cancelAnimationFrame(golb);
            } else {
                golb = requestAnimationFrame(numSlideFun);
            }
            numBox.innerHTML = numText;
        }
        numSlideFun();
    }

    //当日油水耗用量统计图
    dwc();

    function dwc() {
        var dwc = echarts.init(document.getElementById('dwc'));
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
            },
            legend: {
                data: ['用油量', '用水量'],
                textStyle: {
                    color: '#ffffff'
                }
            },
            xAxis: [{
                type: 'category',
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
                axisPointer: {
                    type: 'shadow'
                },
                axisLine: {
                    lineStyle: {
                        color: '#ffffff',
                    }
                }
            }],
            yAxis: [{
                type: 'value',
                name: '使用量',
                min: 0,
                max: 250,
                interval: 50,
                axisLabel: {
                    formatter: '{value}吨'
                },
                axisLine: {
                    lineStyle: {
                        color: '#ffffff',
                    }
                }
            }],
            series: [{
                    name: '用油量',
                    type: 'bar',
                    data: [76.7, 135.6, 162.2, 32.6, 105.0, 215.4, 248.3],
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    { offset: 0, color: '#a45a88' },
                                    { offset: 0.5, color: '#5d48af' },
                                    { offset: 1, color: '#1d43d1' }
                                ]
                            )
                        }
                    }
                },
                {
                    name: '用水量',
                    type: 'bar',
                    data: [80.7, 111.6, 157.2, 61.6, 115.9, 183.4, 231.3],
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    { offset: 0, color: '#01b7df' },
                                    { offset: 0.5, color: '#0180df' },
                                    { offset: 1, color: '#0a55df' }
                                ]
                            )
                        }
                    }
                }
            ]
        };
        dwc.setOption(option);
    }

    //当日到岗人数与未到岗人数占比统计图
    duty();

    function duty() {
        var duty = echarts.init(document.getElementById('duty'));
        option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data: ['到岗人数', '未到岗人数'],
                textStyle: {
                    color: '#ffffff'
                }
            },
            toolbox: {
                feature: {
                    saveAsImage: { show: true }
                }
            },
            series: [{
                name: '比例',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '15',
                            fontWeight: 'bold',
                            color: '#ffffff'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                        value: 317,
                        name: '到岗人数',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        { offset: 0, color: '#23cdc2' },
                                        { offset: 0.5, color: '#3ac189' },
                                        { offset: 1, color: '#5db441' }
                                    ]
                                )
                            }
                        }
                    },
                    {
                        value: 45,
                        name: '未到岗人数',
                        itemStyle: {
                            color: '#112376'
                        }
                    }
                ],
            }]
        };
        duty.setOption(option);
    }

    //报警次数统计图
    alarm();

    function alarm() {
        var alarm = echarts.init(document.getElementById('alarm'));
        option = {
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
                data: ['报警次数'],
                textStyle: {
                    color: '#ffffff'
                }
            },
            toolbox: {
                feature: {
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
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
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
                axisLine: {
                    lineStyle: {
                        color: '#ffffff',
                    }
                }
            }],
            yAxis: [{
                type: 'value',
                axisLabel: {
                    formatter: '{value}次'
                },
                axisLine: {
                    lineStyle: {
                        color: '#ffffff',
                    }
                },
            }],
            series: [{
                name: '报警次数',
                type: 'line',
                stack: '总量',
                areaStyle: {},
                data: [81, 94, 60, 178, 145, 160, 77],
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                { offset: 0, color: '#2db6d4' },
                                { offset: 0.7, color: '#263b79' },
                                { offset: 1, color: '#122058' }
                            ]
                        )
                    }
                }
            }]
        };
        alarm.setOption(option);
    }

    //人工成本统计图
    labor();

    function labor() {
        var labor = echarts.init(document.getElementById('labor'));
        option = {
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
                data: ['人工成本'],
                textStyle: {
                    color: '#ffffff'
                }
            },
            toolbox: {
                feature: {
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
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
                        color: '#ffffff',
                    }
                }
            }],
            yAxis: [{
                type: 'value',
                axisLabel: {
                    formatter: '{value}万'
                },
                axisLine: {
                    lineStyle: {
                        color: '#ffffff',
                    }
                }
            }],
            series: [{
                name: '人工成本',
                type: 'line',
                stack: '总量',
                areaStyle: {},
                smooth: true,
                data: [81, 94, 60, 178, 145, 160, 77, 50, 66, 101, 91, 155],
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                { offset: 0, color: '#2dae09' },
                                { offset: 0.8, color: '#106046' },
                                { offset: 1, color: '#0e315d' }
                            ]
                        )
                    }
                }
            }]
        };
        labor.setOption(option);
    }

    //油耗成本统计图
    fuel()

    function fuel() {
        var fuel = echarts.init(document.getElementById('fuel'));
        option = {
            title: {
                text: '油耗成本',
                textStyle: {
                    color: '#ffffff',
                    fontSize: 14
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
            },
            xAxis: {
                type: 'value',
                axisLabel: {
                    formatter: '{value}元'
                },
                boundaryGap: [0, 0.01],
                axisLine: {
                    lineStyle: {
                        color: '#ffffff',
                    }
                }
            },
            yAxis: {
                type: 'category',
                data: ['2015年', '2016年', '2017年', '2018年', '2019年', '2020年'],
                axisLine: {
                    lineStyle: {
                        color: '#ffffff',
                    }
                }
            },
            series: [{
                type: 'bar',
                data: [18203, 15091, 28197, 19084, 16045, 20097],
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 1, 0,
                            [
                                { offset: 0, color: '#1950ef' },
                                { offset: 0.7, color: '#1975eb' },
                                { offset: 1, color: '#20a2df' }
                            ]
                        )
                    }
                }
            }]
        };
        fuel.setOption(option);
    }

    //巡检统计统计图
    inspection()

    function inspection() {
        var inspection = echarts.init(document.getElementById('inspection'));
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
            },
            legend: {
                data: ['巡检数'],
                textStyle: {
                    color: '#ffffff'
                }
            },
            xAxis: [{
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                axisPointer: {
                    type: 'shadow'
                },
                axisLine: {
                    lineStyle: {
                        color: '#ffffff',
                    }
                }
            }],
            yAxis: [{
                type: 'value',
                name: '巡检数',
                min: 0,
                max: 30,
                interval: 5,
                axisLabel: {
                    formatter: '{value}次'
                },
                axisLine: {
                    lineStyle: {
                        color: '#ffffff',
                    }
                }
            }],
            series: [{
                name: '巡检数',
                type: 'bar',
                data: [13, 21, 18, 7, 11, 27, 13, 9, 4, 24, 19, 12],
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                { offset: 0, color: '#554df6' },
                                { offset: 0.5, color: '#327bf4' },
                                { offset: 1, color: '#15b1ea' }
                            ]
                        )
                    }
                }
            }]
        };
        inspection.setOption(option);
    }
});
var BigScreen={
    toIndex:function () {
        window.location.href=MyObject.ctxPath+'/index';
    }
}