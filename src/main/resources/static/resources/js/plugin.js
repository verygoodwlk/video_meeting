/**
 * 打开弹出框
 */
function openDialog(div, title, w, h){
    if (w == null){
        w = 700;
    }

    if(h == null){
        h = 600;
    }

    $("#" + div).dialog({
        title:title,
        width:w,
        height:h,
        modal:true
    });
}

//关闭弹出框
function closeDialog(div){
    $("#" + div).dialog("close");
}


/**
 * 创建一个Ztree
 */
function createZtree(divid, data, param, did){
    //生成ztree
    var setting = {
        data: {
            key: {
                name: param.name
            },
            simpleData: {
                enable: true,
                pIdKey: param.pid
            }
        },
        view: {
            showIcon: param.icon
        },
        callback: {
            onClick: param.onclick
        },
        check: {
            enable: param.check != null ? param.check : false,
            chkboxType: param.checkType != null ? param.checkType : { "Y": "ps", "N": "ps" }
        }
    };
    var zNodes = data;

    //初始化zTree
    var ztreeObject = $.fn.zTree.init($("#" + divid), setting, zNodes);
    ztreeObject.expandAll(param.expand);//全部展开

    if(did != null){
        //如果did不为空，则表示did说对应的树形节点需要高亮显示

        //获得did说对应的节点
        var nodes = ztreeObject.getNodeByParam("id", did, null);
        //让这个节点呈现高亮状态
        ztreeObject.selectNode(nodes);
    }

    return ztreeObject;
}