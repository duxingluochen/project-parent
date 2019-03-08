$(function(){
    //搜索下拉框赋值
    $(".dropdown-menu a").click(function(){
        var txt = $(this).text();
        $("#txt").html(txt + " <span class=\"caret\"></span>");
    });

    //树形菜单点击事件
    $(".tree>li>a").click(function(){
        $(this).addClass('tree-style').parent().siblings().children('a').removeClass('tree-style');
        $(this).children('i').removeClass('fa-plus-square-o').addClass('fa-minus-square-o').parent().
        parent().siblings().children('a').children('i').removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
        $(this).next().slideDown('fast').parent().siblings().children('.tree-two').slideUp('fast');
        //清除影响
        $(".tree-two>li>a>i").removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
        $(".tree-two>li>a, .tree-three>li>a").removeClass('tree-style');
        $(".tree-three").hide();
    });

    //车辆、摄像头目录点击事件
    $(".tree-two>li>a").click(function(){
        $(this).addClass('tree-style').parent().siblings().children('a').removeClass('tree-style');
        $(this).children('i').removeClass('fa-plus-square-o').addClass('fa-minus-square-o').parent().
        parent().siblings().children('a').children('i').removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
        $(this).next().slideDown('fast').parent().siblings().children('.tree-three').slideUp('fast');
        //清除影响
        $(".tree-three>li>a").removeClass('tree-style');
    });

    //车里、摄像头自身点击事件
    $(".tree-three>li>a").click(function(){
        $(this).addClass('tree-style').parent().siblings().children('a').removeClass('tree-style');
    });
});