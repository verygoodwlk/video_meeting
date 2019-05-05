Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};

Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};


var uploader;
var files = [];
/**
 * 初始化上传队列
 */
function uploadInit(){
    //初始化上传控件
    uploader = WebUploader.create({
        // swf文件路径
        swf: 'widget/webuploader/Uploader.swf',
        // 文件接收服务端。
        // server: [[${#request.getContextPath()}]] + '/res/upload',
        server: '/res/upload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });

    //设置入队
    uploader.on("fileQueued", function (file) {
        //预处理文件名称
        var n = file.name;
        if (n.length > 10) {
            n = n.substring(0, 10);
            n += "...";
        }


        //上传列表
        var fileHtml = "<tr id='tr_" + file.id + "'>" +
            "<td>" + n + "</td>" +
            "<td id='progress_" + file.id + "'>待上传" +
            "<button onclick=\"removeQueue('" + file.id + "')\" type='button' class='close'>" +
            "<span>&times;</span>" +
            "</button>" +
            "</td>" +
            "</tr>";

        $("#upload_list").append(fileHtml);
    });

    //设置进度条
    uploader.on('uploadProgress', function (file, percentage) {
        // console.log("进度：" + (percentage * 100 + '%'));
    });

    //设置成功事件
    uploader.on("uploadSuccess", function (file) {
        $("#progress_" + file.id).html("<font color='green'>上传成功</font>");
        files.push(file.name);
    });

    //设置失败事件
    uploader.on("uploadError", function (file) {
        $("#progress_" + file.id).html("<font color='red'>上传失败</font>");
    });
}

//初始化上传
$(function () {
    uploadInit();
});

/**
 * 移除队列
 */
function removeQueue(fid) {
    //将文件从列表中移除
    $("#tr_" + fid).remove();
    //将文件从队列中移除
    uploader.removeFile(fid, true);
}

/**
 * 移除文件数组
 * @param fname
 */
function removeFiles(fid, fname){
    $("#tr_old_" + fid).remove();
    for(var i = 0; i < files.length; i++){
        if(files[i] == fname){
            files.splice(i, 1);
        }
    }
}

/**
 * 开始上传
 */
function start_upload() {
    if (uploader) {
        uploader.upload();
    }
}

/**
 * 重置上传
 */
function resetUpload(){
    //清空上传列表
    $("#upload_list").html("");
    files=[];
}

//回显文件列表
function showUpdate(mp3){
    files = JSON.parse(mp3);
    for(var i = 0; i < files.length; i++){
        var fileHtml = "<tr id='tr_old_" + i + "'>" +
            "<td>" + files[i] + "</td>" +
            "<td id='progress_old_" + i + "'><font color='green'>已上传</font>" +
            "<button onclick=\"removeFiles('" + i + "','" + files[i] + "')\" type='button' class='close'>" +
            "<span>&times;</span>" +
            "</button>" +
            "</td>" +
            "</tr>";

        $("#upload_list").append(fileHtml);
    }
}