var taskname=null;
var terminal=null;
var startDate=null;
var startTime=null;
var stopDate=null;
var duration=null;
var playOrder=null;
var weekMask=null;
var solution=null;
var files = null;
var terminal = null;

var area=null;
var port=null;
var isExternalMusic=null;

var speed=null;
var loopnum=null;
var info=null;
/**
 * 校验表单
 */
function check_Form(taskt){

    if(taskname != null && taskname.trim() == ""){
        alert("任务名称不能为空！");
        return false;
    }

    if(terminal != null && terminal.length <= 0){
        alert("终端选择不能为空！");
        return false;
    }

    if(isExternalMusic == null || (isExternalMusic != null && !isExternalMusic) ){
        if(files != null && files.length <= 0){
            alert("上传曲目不能为空！");
            return false;
        }
    }

    //------------
    if(startDate != null && startDate.trim() == ""){
        alert("开始日期不能为空！");
        return false;
    }

    if(startTime != null && startTime.trim() == ""){
        alert("开始时间不能为空！");
        return false;
    }

    if(stopDate != null && $("#endTime").prop("disabled")==false && stopDate.trim() == ""){
        alert("结束时间不能为空！");
        return false;
    }

    if(duration != null && $("#durtime").prop("disabled")==false && duration == ""){
        alert("持续时间不能为空！");
        return false;
    }

    var r = /^\+?[1-9][0-9]*$/;　　//正整数
    var flag=r.test(duration);
    if(duration != null && $("#durtime").prop("disabled")==false && !flag){
        alert("持续时间必须为整数值！");
        return false;
    }

    if(weekMask != null && parseInt(playOrder) == 2 && weekMask == 0){
        alert("星期选择不能为空！");
        return false;
    }

    if(solution != null && solution.trim() == ""){
        alert("所选方案不能为空！");
        return false;
    }

    if(area != null && area.trim() == ""){
        alert("报警区域不能为空！");
        return false;
    }

    if(port != null && port.trim() == ""){
        alert("报警设备端口不能为空！");
        return false;
    }

    if(speed != null && speed.trim() == ""){
        alert("语速不能为空！");
        return false;
    }

    if(loopnum != null && loopnum.trim() == ""){
        alert("循环不能为空！");
        return false;
    }

    if(info != null && info.trim() == ""){
        alert("文本信息不能为空！");
        return false;
    }


    return true;
}


/**
 * 重置弹出框
 */
function reset_Model(taskt) {
    $("#taskname").val("Region");
    $("#loopType").val(1);
    $("#playOrder").val(1);
    $("#startDate").val("");
    $("#startTime").val("");
    $("#endTime").val("");
    $("#endTime").attr("disabled", "disabled");
    $("#checkbox_end").attr("checked",false);
    $("#durtime").val("");
    $("#durtime").attr("disabled", "disabled");
    $("#checkbox_dur").attr("checked",false);
    $("#start").val("");
    $("input[name='week']:checked").attr("checked", false);
    $("input[name='week']").attr("disabled","disabled");

    $("#area").val("");
    $("#fireTerminal").val(1);
    $("#port").val("");
    $("#isLevel").val(1);
    $("#isExternalMusic").attr("checked",false);
    $("#externalFireTerminal").val(1);

    $("#reporter").val(1);
    $("#speed").val("");
    $("#loopnum").val("");
    $("#info").val("");

    // $("#taskname").val("");
    //
    //
    // if(taskt == 5){
    //     //--------------------------实时音乐---------------------------
    //     // $("#taskname").val("");
    // } else if(taskt == 1){
    //     //--------------------------定时音乐---------------------------
    //     $("#loopType").val(1);
    //     $("#playOrder").val(1);
    //     $("#startDate").val("");
    //     $("#startTime").val("");
    //     $("#endTime").val("");
    //     $("#endTime").attr("disabled", "disabled");
    //     $("#checkbox_end").attr("checked",false);
    //     $("#durtime").val("");
    //     $("#durtime").attr("disabled", "disabled");
    //     $("#checkbox_dur").attr("checked",false);
    //     $("#start").val("");
    //     $("input[name='week']:checked").attr("checked", false);
    //     $("input[name='week']").attr("disabled","disabled");
    // } else if(taskt == 2){
    //     //--------------------------定时采集---------------------------
    //     $("#playOrder").val(1);
    //     $("#startDate").val("");
    //     $("#startTime").val("");
    //     $("#endTime").val("");
    //     $("#endTime").attr("disabled", "disabled");
    //     $("#checkbox_end").attr("checked",false);
    //     $("#durtime").val("");
    //     $("#durtime").attr("disabled", "disabled");
    //     $("#checkbox_dur").attr("checked",false);
    //     $("#start").val("");
    //     $("input[name='week']:checked").attr("checked", false);
    //     $("input[name='week']").attr("disabled","disabled");
    // } else if(taskt == 3){
    //     //--------------------------消防报警---------------------------
    //     $("#area").val("");
    //     $("#fireTerminal").val(1);
    //     $("#port").val("");
    //     $("#isLevel").val(1);
    //     $("#isExternalMusic").attr("checked",false);
    //     $("#externalFireTerminal").val(1);
    // } else if(taskt == 4){
    //     //--------------------------语音合成---------------------------
    //     $("#playOrder").val(1);
    //     $("#startDate").val("");
    //     $("#startTime").val("");
    //     $("#endTime").val("");
    //     $("#endTime").attr("disabled", "disabled");
    //     $("#checkbox_end").attr("checked",false);
    //     $("#reporter").val(1);
    //     $("#speed").val("");
    //     $("#loopnum").val("");
    //     $("#info").val("");
    //     $("input[name='week']:checked").attr("checked", false);
    //     $("input[name='week']").attr("disabled","disabled");
    // }
}

/**
 * 重现表单
 * @param data
 * @param taskt
 */
function showData(data, taskt){
    //--------------------------------------------------------
    var taskname = data.taskname;
    var looptype = data.looptype;
    var mp3 = data.mp3;
    var users = data.users;
    //获得任务类型
    var playOrder = data.playOrder;
    //获得开始日期
    var startDate = data.startDate;
    //获得开始时间
    var startTime = data.startTime;
    //获得结束时间
    var stopDate = data.stopDate;
    //获得持续时间
    var duration = data.duration;
    //获得提前开始
    var start = data.startinadvance;
    //获得星期几
    var weekMask = data.weekMask;
    //获得音量
    var volume = data.volume;

    var collectionclient = data.collectionclient;

    var area = data.area;
    var fireTerminal = data.fireTerminal;
    var port = data.port;
    var isLevel = data.isLevel;
    var isExternalMusic = data.isExternalMusic;
    var externalFireTerminal = data.externalFireTerminal;

    var reporter = data.reporter;
    var speed = data.speed;
    var loopnum = data.loopnum;
    var info = data.info;

    //设置默认值
    if(taskname){
        $("#taskname").val(taskname);
    }
    if(looptype >= 0){
        $("#loopType").val(looptype);
    }
    if(mp3){
        showUpdate(mp3);
    }
    if(users){
        showUsers(users);
    }
    //--------------------------------------------------------
    if(playOrder){
        $("#playOrder").val(playOrder);
    }
    if(startDate){
        $("#startDate").val(startDate);
    }
    if(startTime){
        $("#startTime").val(startTime);
    }
    if(stopDate){
        $("#endTime").val(stopDate);
    }
    if(duration){
        $("#durtime").val(duration);
    }
    if(start){
        $("#start").val(start);
    }

    if(weekMask){

        //TODO 设置星期
        var weeksArray = [2,4,8,16,32,64,128];
        for(var i = 0; i < weeksArray.length; i++){
            if ((weekMask & weeksArray[i]) > 0){
                $("input[type='checkbox'][name='week'][value='" + weeksArray[i] + "']").attr("checked", true);
            }
        }
    }
    if(volume){
        //TODO 设置修改的音量
        // var slider = $("#volume").data("ionRangeSlider");
        // slider.update({
        //     from: volume
        // });
        $("#volume input").val(volume);
    }
    if(stopDate){
        $("#checkbox_end").attr("checked", true);
        $("#endTime").removeAttr("disabled");
    } else {
        $("#checkbox_end").attr("checked", false);
        $("#endTime").attr("disabled", "disabled");
    }

    if(duration){
        $("#checkbox_dur").attr("checked", true);
        $("#durtime").removeAttr("disabled");
    } else {
        $("#checkbox_dur").attr("checked", false);
        $("#durtime").attr("disabled", "disabled");
    }

    if(playOrder == 2){
        $("input[name='week']").each(function(){
            $(this).removeAttr("disabled");
        });
    }

    //--------------------------------------------------------
    //设置默认值
    if(collectionclient){
        $("#collection_client").val(collectionclient);
    }
    //--------------------------------------------------------
    //设置默认值
    if(area){
        $("#area").val(area);
    }
    if(fireTerminal){
        $("#fireTerminal").val(fireTerminal);
    }
    if(port){
        $("#port").val(port);
    }
    if(isLevel){
        $("#isLevel").val(isLevel);
    }
    if(externalFireTerminal){
        $("#externalFireTerminal").val(externalFireTerminal);
    }
    if(isExternalMusic && isExternalMusic == 1){
        $("#isExternalMusic").attr("checked", true);
        $("#externalFireTerminal").removeAttr("disabled");
    } else {
        $("#isExternalMusic").attr("checked", false);
        $("#externalFireTerminal").attr("disabled", "disabled");
    }
    //--------------------------------------------------------
    //设置默认值
    if(reporter){
        $("#reporter").val(reporter);
    }
    if(speed){
        $("#speed").val(speed);
    }
    if(loopnum){
        $("#loopnum").val(loopnum);
    }
    if(info){
        $("#info").val(info);
    }
}

/**
 * 是否执行当前操作
 * type 1 - 修改 2 - 删除
 * @param type
 */
function isAction(type){

    var task = $("[alt='del']")[0];
    if(!task){
        alert("请选中需要编辑的任务！");
        return;
    }

    var tid = task.getAttribute("taskidstr");

    $.ajax({
        url:"/web/task/isaction",
        data:{"taskid":tid},
        success: function(data){
            if(!data){
                if(type == 1){
                    //修改
                    open_update_Model();
                } else if(type == 2){
                    //删除
                    delete_task();
                }
            } else {
                alert("执行中的任务不能执行'" + (type == 1 ? '编辑' : '删除') + "'操作！");
            }
        }
    });
}