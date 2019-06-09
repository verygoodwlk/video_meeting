/**
 * 初始化树形结构
 */
var ztreeObj;
function initClients(fun) {
    $.ajax({
        url: "/client/group",
        success: function (data) {
            var zTreeNodes = data;

            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: "pid"
                    },
                    key: {
                        name: "gname"
                    }
                },
                check: {
                    enable: true
                }
            };

            ztreeObj = $.fn.zTree.init($("#ztree_div"), setting, zTreeNodes);
            ztreeObj.expandAll(true);

            if(fun){
                fun(data);
            }
        },
        dataType: "json"
    });
}

/**
 * 全选终端
 */
function checkall(flag) {
    ztreeObj.checkAllNodes(flag);
}

/**
 * 回显节点
 */
function showUsers(users){

    if(!users){
        return ;
    }

    var us = JSON.parse(users);
    //获得全部节点
    var nodes = ztreeObj.transformToArray(ztreeObj.getNodes());

    A:for(var i = 0; i < us.length; i++){
        for(var j = 0; j < nodes.length; j++){
            //console.log(us[i] + " " + nodes[j].userid + " " + nodes[j].gname);
            if(us[i] == nodes[j].userid){
                ztreeObj.checkNode(nodes[j], true, true);
                //获得父节点
                if(nodes[j].getParentNode()){
                    ztreeObj.checkNode(nodes[j].getParentNode(), true, false);
                }
                continue A;
            }
        }
    }
}