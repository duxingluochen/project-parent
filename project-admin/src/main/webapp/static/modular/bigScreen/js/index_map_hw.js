var map, base, graLayer;

require([
    "esri/map",
    "esri/layers/ArcGISTiledMapServiceLayer",
    "esri/layers/ArcGISDynamicMapServiceLayer",
    "esri/tasks/IdentifyTask",
    "esri/tasks/IdentifyParameters",
    "esri/graphic",
    "esri/layers/GraphicsLayer",
    "esri/geometry/Point",
    "esri/SpatialReference",
    "esri/symbols/PictureMarkerSymbol",
    "dojo/dom",
    "dojo/on",
    "dojo/domReady!"
], function(
    Map, ArcGISTiledMapServiceLayer, ArcGISDynamicMapServiceLayer, IdentifyTask, IdentifyParameters,
    Graphic, GraphicsLayer, Point, SpatialReference, PictureMarkerSymbol, dom, on
) {
    map = new Map("map", {
        logo: false,
        slider: false,
        center: [107.835707, 29.864088],
        spatialReference: 4326
    });

    map.setScale(2900000);

    //影像服务
    base = new ArcGISDynamicMapServiceLayer("http://192.168.1.154:6080/arcgis/rest/services/ShuangJiang/Map/MapServer");

    //坐标点图层
    graLayer = new GraphicsLayer();

    //添加图层到map对象中
    map.addLayers([base, graLayer]);

    DrawCoordinate(106.526026, 29.615348, 1);
    //动态绘制点方法
    function DrawCoordinate(lon, lat, id) {
        //创建坐标点
        var point = new Point(lon, lat, new SpatialReference({ wkid: 4326 }));
        //添加图片
        var symbol = new PictureMarkerSymbol(rootPath + 'static/modular/bigScreen/image/car.png', 30, 23);
        //创建graphic对象
        var graphic = new Graphic(point, symbol);
        //传入id值
        graphic.text = id;
        //将graphic对象加入坐标点图层
        graLayer.add(graphic);
    }

    //动态绘制点点击事件
    on(graLayer, "click", function(evt) {

        var id = evt.graphic.text; //获取id
        var lat = evt.graphic.geometry.x; //获取经度
        var lon = evt.graphic.geometry.y; //获取纬度
        ZoomtoPoint(lat, lon, 150000);
        //console.log("id:" + id + "，经度：" + lat + "，纬度：" + lon);

        //设置弹窗标题
        map.infoWindow.setTitle("基本信息");
        //设置弹窗内容
        map.infoWindow.setContent(
            "<p>ID：" + id + "</p>" +
            "<p>经度：" + lat + "</p>" +
            "<p>纬度：" + lon + "</p>"
        );
        //显示弹窗
        map.infoWindow.show(evt.mapPoint, map.getInfoWindowAnchor(evt.screenPoint));
    });

    //鼠标移出事件
    // on(graLayer, "mouse-out", function(evt) {
    //     map.infoWindow.hide();
    // });

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