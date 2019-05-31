$(function(){
    setInterval(function(){
        updateTaskStatus();
    }, 5000);
});

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

                //设置状态
                var html = "";
                if(status == 0){
                    html += "任务空闲";
                } else if(status == 1){
                    html += "执行中";
                } else if(status == 2){
                    html += "停止";
                } else if(status == 3){
                    html += "等待中";
                }
                $("#task_status_" + taskid).html(html);

                //设置歌曲
                $("#task_mp3_" + taskid).html(mp3);
            }

        },"json");
    }
}