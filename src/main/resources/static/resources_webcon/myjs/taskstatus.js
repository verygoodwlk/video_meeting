$(function(){
    setInterval(function(){
        updateClientStatus();
        updateTaskStatus();
    }, 1000);
});

/**
 * 更新终端状态
 */
function updateClientStatus(){
    //获得任务列表
    var clientList = $("[id^='tr_client_']");

    var uids = [];
    if(clientList && clientList.length > 0){
        for(var i = 0; i < clientList.length; i++){
            // console.log("当前页面的任务id：" + taskList[i].getAttribute("taskid"));
            uids.push(clientList[i].getAttribute("uid"));
        }

        $.post("/web/task/updateClientStatus", {"uids": uids}, function(data){

            for(var i = 0; i < data.length; i++){
                //获得当前任务id
                var dinfo = data[i];

                //从结果中获取任务id
                var uid = dinfo.uid;
                //获得当前任务的状态
                var status = dinfo.status;
                //获得当前播放歌曲
                var mp3 = dinfo.mp3;
                //获得当前


                //设置状态
                var html = "";
                if(status == 0){
                    html += "任务空闲";
                } else if(status == 1){
                    html += "执行中";
                } else if(status == 2){
                    html += "任务空闲";
                } else if(status == 3){
                    html += "暂停";
                }
                $("#client_status_" + uid).html(html);

                //设置歌曲
                $("#client_mp3_" + uid).html(mp3);
            }

        },"json");
    }
}

/**
 * 更新任务状态
 */
function updateTaskStatus(){
    //获得任务列表
    var taskList = $("[id^='tr_task_']");

    var taskids = [];
    if(taskList && taskList.length > 0){
        for(var i = 0; i < taskList.length; i++){
            // console.log("当前页面的任务id：" + taskList[i].getAttribute("taskid"));
            taskids.push(taskList[i].getAttribute("taskidstr"));
        }

        $.post("/web/task/updateStatus", {"taskids": taskids}, function(data){

            for(var i = 0; i < data.length; i++){
                //获得当前任务id
                var dinfo = data[i];

                //从结果中获取任务id
                var taskid = dinfo.taskid;
                //获得当前任务的状态
                var status = dinfo.status;
                //获得当前播放歌曲
                var mp3 = dinfo.mp3;
                //获得当前持续时间
                var duration = dinfo.duration;
                //获得当前任务类型
                var type = dinfo.type;
                //获得当前更新时间
                var startDate = dinfo.startDate;
                //获得当前任务总进度
                var allduration = dinfo.allduration;
                //获得当前任务进度
                var nowduration = dinfo.nowduration;

                //设置状态
                if(status){
                    var html = "";
                    if(status == 0 || status == 2){
                        html += "任务空闲";

                        $("#task_mp3_" + taskid).html("");
                        $("#task_allduration_" + taskid).html(s2str(0));
                        $("#task_nowduration_" + taskid).html(s2str(0));
                    } else if(status == 1){
                        html += "执行中";

                        //设置歌曲
                        if(mp3){
                            $("#task_mp3_" + taskid).html(mp3);
                        }

                        //设置任务总进度
                        if(allduration) {
                            $("#task_allduration_" + taskid).html(s2str(allduration));
                        }

                        if(nowduration){
                            $("#task_nowduration_" + taskid).html(s2str(nowduration));
                        }
                    } else if(status == 3){
                        html += "暂停";
                    }
                    $("#task_status_" + taskid).html(html);
                }


                //设置持续时间
                if(duration){
                    $("#task_duration_" + taskid).html(s2str(duration));
                }

                //0:普通状态  1:一次性任务结束 - 删除   2:每天任务结束 - 更新开始时间
                if(type == 1){
                    //删除一次性任务
                    $("#tr_task_" + taskid).remove();
                } else if(type == 2){
                    //更新开始时间
                    $("#task_startdate_" + taskid).html(startDate);
                }
            }

        },"json");
    }
}

/**
 * 秒转字符串
 * @param secoendTime
 */
function s2str(secoendTime){
    var str = "";
    var m = parseInt(secoendTime/60);//获得多少分钟

    if(m < 10){
        str += "0" + m;
    } else {
        str += m;
    }

    str += ":";

    var s = parseInt(secoendTime%60);//获得多少秒
    if(s < 10){
        str += "0" + s;
    } else {
        str += s;
    }

    return str;
}