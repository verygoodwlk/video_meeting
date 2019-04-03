/**
 * 动态计算高度
 */
function autoHeight(fun){
    //获得窗口总高度
    var h = $(document.body).height();

    //获得头部高度
    var headers = $(".myheader");
    for(var i = 0; i < headers.length; i++){
        // alert($(headers[i]).height());
        h -= $(headers[i]).height();
    }

    $(".h_1").css("height", h + "px");
    $(".h_2").css("height", h/2 + "px");
    $(".h_3").css("height", h/3 + "px");
    $(".h_4").css("height", h/4 + "px");

    if(fun){
        fun(h);
    }
}