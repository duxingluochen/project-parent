//人员加班费用
person();

function person() {
    var person = echarts.init(document.getElementById('person'));
    option = {
        backgroundColor: '#ffffff',
        title: {
            text: '人员加班费用',
            textStyle: {
                color: '#505050',
                fontSize: 14
            }
        },
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
            data: ['人员加班费用'],
            textStyle: {
                color: '#505050'
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
        xAxis: {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisLine: {
                lineStyle: {
                    color: '#505050',
                }
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}万'
            },
            axisLine: {
                lineStyle: {
                    color: '#505050',
                }
            }
        },
        series: [{
            data: [40, 51, 39, 49, 28, 37, 35, 49, 62, 34, 26, 79],
            type: 'line',
            itemStyle: {
                color: '#09aa73',
            }
        }]
    };
    person.setOption(option);
}

//油使用量
oil()

function oil() {
    var oil = echarts.init(document.getElementById('oil'));
    option = {
        backgroundColor: '#ffffff',
        title: {
            text: '油耗费用',
            textStyle: {
                color: '#505050',
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
                    color: '#505050',
                }
            }
        },
        yAxis: {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisLine: {
                lineStyle: {
                    color: '#505050',
                }
            }
        },
        series: [{
            type: 'bar',
            data: [18203, 28197, 19084, 17045, 20297,18203, 28197, 12084, 16745, 20097,18203, 28297],
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 1, 0,
                        [
                            { offset: 0, color: '#07ca9a' },
                            { offset: 0.5, color: '#08beb9' },
                            { offset: 1, color: '#1eb3d3' }
                        ]
                    )
                }
            }
        }]
    };
    oil.setOption(option);
}

//水使用量
water();

function water() {
    var water = echarts.init(document.getElementById('water'));
    option = {
        backgroundColor: '#ffffff',
        title: {
            text: '水耗费用',
            textStyle: {
                color: '#505050',
                fontSize: 14
            }
        },
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
            data: ['水耗费用'],
            textStyle: {
                color: '#505050'
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
                    color: '#505050',
                }
            }
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                formatter: '{value}元'
            },
            axisLine: {
                lineStyle: {
                    color: '#505050',
                }
            }
        }],
        series: [{
            name: '水耗费用',
            type: 'line',
            stack: '总量',
            areaStyle: {},
            smooth: true,
            data: [8122, 8238, 7733, 8821, 7472, 7653, 8521, 9233, 9457, 7060, 8121, 8222],
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            { offset: 0, color: '#50d011' },
                            { offset: 0.7, color: '#b5dda1' },
                            { offset: 1, color: '#eaf9e3' }
                        ]
                    )
                }
            }
        }]
    };
    water.setOption(option);
}

//设施采购费用
get();

function get() {
    var get = echarts.init(document.getElementById('get'));
    option = {
        backgroundColor: '#ffffff',
        title: {
            text: '设施采购费用',
            textStyle: {
                color: '#505050',
                fontSize: 14
            }
        },
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
            data: ['设施采购费用'],
            textStyle: {
                color: '#505050'
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
                    color: '#505050',
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
                    color: '#505050',
                }
            },
        }],
        series: [{
            name: '设施采购费用',
            type: 'line',
            stack: '总量',
            areaStyle: {},
            data: [81, 88, 77, 69, 67, 70, 77, 66, 89, 73, 67, 68],
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            { offset: 0, color: '#0ca2a7' },
                            { offset: 0.7, color: '#9feae2' },
                            { offset: 1, color: '#e9faf8' }
                        ]
                    )
                }
            }
        }]
    };
    get.setOption(option);
}

//维修费用
change()

function change() {
    var change = echarts.init(document.getElementById('change'));
    option = {
        backgroundColor: '#ffffff',
        title: {
            text: '维修费用',
            textStyle: {
                color: '#505050',
                fontSize: 14
            }
        },
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
            data: ['维修费用'],
            textStyle: {
                color: '#505050'
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
                    color: '#505050',
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
                    color: '#505050',
                }
            }
        }],
        series: [{
            name: '维修费用',
            type: 'bar',
            data: [68, 67, 73, 89, 66, 77, 70, 67, 69, 77, 88, 81],
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            { offset: 0, color: '#4cd6f9' },
                            { offset: 0.5, color: '#78ece9' },
                            { offset: 1, color: '#9cf0d3' }
                        ]
                    )
                }
            }
        }]
    };
    change.setOption(option);
}