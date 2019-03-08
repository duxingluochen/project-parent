var map, base, vtlayer, graLayer;

require([
    "esri/map",
    "esri/layers/ArcGISTiledMapServiceLayer",
    "esri/layers/ArcGISDynamicMapServiceLayer",
    "esri/graphic",
    "esri/layers/GraphicsLayer",
    "esri/geometry/Point",
    "esri/SpatialReference",
    "esri/symbols/PictureMarkerSymbol",
    "dojo/dom",
    "dojo/on",
    "dojo/domReady!"
], function(
    Map, ArcGISTiledMapServiceLayer, ArcGISDynamicMapServiceLayer, Graphic,
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

    //汽车图层
    graLayer = new GraphicsLayer();

    //添加图层到map对象中
    map.addLayers([vtlayer, graLayer]);

    DrawCoordinate(106.548868, 29.613583, 1);
    //汽车绘制方法
    function DrawCoordinate(lon, lat, id) {
        //创建坐标点
        var point = new Point(lon, lat, new SpatialReference({ wkid: 4326 }));
        //添加图片
        var symbol = new PictureMarkerSymbol(MyObject.ctxPath+ '/static/modular/monitoring/image/icon_01.png', 25, 20);
        //创建graphic对象
        var graphic = new Graphic(point, symbol);
        //传入id值
        graphic.text = id;
        //将graphic对象加入坐标点图层
        graLayer.add(graphic);
    }

    //汽车点击事件
    on(graLayer, "click", function(evt) {
        var id = evt.graphic.text; //获取id
        var lat = evt.graphic.geometry.x; //获取经度
        var lon = evt.graphic.geometry.y; //获取纬度
        ZoomtoPoint(lat, lon, 25000);
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

    //影像地图、矢量地图切换
    $(".btn-opt").click(function(){
        map.removeAllLayers();
        var text = $(this).text();
        if(text=="矢量地图"){
            map.addLayers([vtlayer, graLayer]);
            $(this).text("影像地图");
            //console.log("已为您切换为矢量地图");
        } else if(text=="影像地图"){
            map.addLayers([base, graLayer]);
            $(this).text("矢量地图");
            //console.log("已为您切换为影像地图");
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