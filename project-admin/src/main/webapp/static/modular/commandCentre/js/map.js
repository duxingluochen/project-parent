var map, base, vtlayer, cqbz, graLayer;

require([
    "esri/map",
    "esri/layers/ArcGISTiledMapServiceLayer",
    "esri/layers/ArcGISDynamicMapServiceLayer",
    "esri/layers/FeatureLayer",
    "esri/graphic",
    "esri/layers/GraphicsLayer",
    "esri/geometry/Point",
    "esri/SpatialReference",
    "esri/symbols/PictureMarkerSymbol",
    "dojo/dom",
    "dojo/on",
    "dojo/domReady!"
], function(
    Map, ArcGISTiledMapServiceLayer, ArcGISDynamicMapServiceLayer, FeatureLayer, Graphic,
    GraphicsLayer, Point, SpatialReference, PictureMarkerSymbol, dom, on
) {
    map = new Map("map", {
        logo: false,
        slider: false,
        center: [106.548868, 29.613583],
        spatialReference: new SpatialReference(3857)
    });

    map.setScale(25000);

    //影像地图
    base = new ArcGISTiledMapServiceLayer(tiled);

    //矢量地图
    vtlayer = new ArcGISTiledMapServiceLayer(vTiled);

    //要素地图
    cqbz = new FeatureLayer(northStation, {"outFields": "*"});

    //汽车图层
    graLayer = new GraphicsLayer();

    //添加图层到map对象中
    map.addLayers([base, cqbz, graLayer]);

    DrawCoordinate(106.548868, 29.613583, 1, "卡车");
    DrawCoordinate(106.538868, 29.615583, 2, "工人");
    //汽车绘制方法
    function DrawCoordinate(lon, lat, id, type) {
        //创建坐标点
        var point = new Point(lon, lat, new SpatialReference({ wkid: 4326 }));
        //添加图片
        if(type =="卡车"){
            var symbol = new PictureMarkerSymbol(MyObject.ctxPath+ '/static/modular/commandCentre/image/car.png', 41, 29);
        } else if(type =="工人"){
            var symbol = new PictureMarkerSymbol(MyObject.ctxPath+ '/static/modular/commandCentre/image/person.png', 40, 45);
        }
        //创建graphic对象
        var graphic = new Graphic(point, symbol);
        //传入id值
        graphic.text = id;
        graphic.type = type;
        //将graphic对象加入坐标点图层
        graLayer.add(graphic);
    }

    //汽车点击事件
    on(graLayer, "mouse-move", function(evt) {
        var type = evt.graphic.type; //获取点类型
        var id = evt.graphic.text; //获取id
        //var lat = evt.graphic.geometry.x; //获取经度
        //var lon = evt.graphic.geometry.y; //获取纬度
        //ZoomtoPoint(lat, lon, 25000);

        if(type =="卡车"){
            //设置弹窗标题
            map.infoWindow.setTitle("车辆基本信息");
            //设置弹窗内容
            map.infoWindow.setContent(
                "<p>点类型：" + type + "</p>" +
                "<p>作业车牌号：" + id + "</p>" +
                "<p>驾驶人：" + type + "</p>" +
                "<p>发车时间：" + id + "</p>" +
                "<p>押运废物：" + type + "</p>"
            );
        } else if(type =="工人"){
            //设置弹窗标题
            map.infoWindow.setTitle("工人基本信息");
            //设置弹窗内容
            map.infoWindow.setContent(
                "<p>点类型：" + type + "</p>" +
                "<p>工人编号：" + id + "</p>" +
                "<p>出发时间：" + type + "</p>" +
                "<p>清除垃圾量：" + id + "</p>"
            );
        }
        //显示弹窗
        map.infoWindow.show(evt.mapPoint, map.getInfoWindowAnchor(evt.screenPoint));
    });

    //鼠标移出事件
    on(graLayer, "mouse-out", function(evt) {
        map.infoWindow.hide();
    });

    //要素服务点击事件
    on(cqbz, "click", function(evt){
        var id = evt.graphic.attributes.objectid;//id
        var name = evt.graphic.attributes.name;//车站名称
        //设置弹窗标题
        map.infoWindow.setTitle("当前作业车辆基本信息");
        //设置弹窗内容
        map.infoWindow.setContent(
            "<p>车牌号：" + id + "</p>" +
            "<p>驾驶员：" + name + "</p>" +
            "<p>联系电话：" + id + "</p>" +
            "<p>押运人：" + name + "</p>" +
            "<p>发车时间：" + id + "</p>" +
            "<p>危废拉运量：" + id + "</p>" +
            "<p>车辆用油量：" + id + "</p>" +
            "<p>车辆用水量：" + id + "</p>"
        );
        //显示弹窗
        map.infoWindow.show(evt.mapPoint, map.getInfoWindowAnchor(evt.screenPoint));
    });

    //影像地图、矢量地图切换
    $(".btn-opt").click(function(){
        map.removeAllLayers();
        var text = $(this).text();
        if(text=="矢量地图"){
            map.addLayers([vtlayer, cqbz, graLayer]);
            $(this).text("影像地图");
        } else if(text=="影像地图"){
            map.addLayers([base, cqbz, graLayer]);
            $(this).text("矢量地图");
        }
    });

    //定位方法
    function ZoomtoPoint(x, y, num) {
        if (x === 0 || y === 0) {
            alert("暂无坐标信息，无法定位！");
            return;
        }
        var cPoint = new Point();
        cPoint.x = x;
        cPoint.y = y;
        map.setScale(num);
        map.centerAt(cPoint);
    }
});