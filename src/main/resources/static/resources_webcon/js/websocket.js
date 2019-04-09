var websocket;
var urls;
/**
 * 初始化websocket连接
 */
function initWs(url, event){
	urls = url;
	if(window.WebSocket){
        if(urls){
            console.log("获取到websocket地址为：" + urls);

            //连接websocket服务器
            websocket = new WebSocket(urls);
            //设置回调方法
            callback(event);
        } else {
            //没有请求到地址，开始进行重连操作
            reconn(event);
        }
	} else {
		alert("该浏览器版本太低，请更换浏览器或者升级版本，否则可能会影响到您的正常使用！");
	}
}


function callback(event){
	if(websocket){
		websocket.onopen = function(e){
			//发送心跳机制
			heart();
			//定时关闭连接
            closeConn();
			//自定义处理方法
			event.onopen(e);
		};
		websocket.onclose = function(e){
			//进行重连
			reconn(event);
			//停止发送心跳
			if(time){
				clearTimeout(time);
			}
			
			event.onclose(e);
		};
		websocket.onerror = event.onerror;
		websocket.onmessage = function(e){

            // console.log("获得服务器端消息" + e.data);
            if (closeTime){
                clearTimeout(closeTime);
                closeConn();
            }

            if(e.data != "{\"id\":\"ack\",\"response\":\"ack\"}"){
                //调用自定义的消息处理方法
                event.onmessage(e);
			}
		}
	}
}

/**
 * 发送消息给服务器
 */
function sendMsg(content){
	if(websocket){
		websocket.send(content);
	} else {
		console.log("服务器连接异常！");
	}
}

/**
 * 发送JSON对象到服务器
 */
function sendObjMsg(obj){
	sendMsg(JSON.stringify(obj));
}

/**
 * 重连机制
 */
function reconn(event){
	console.log("开始进行重连....");
	setTimeout(function(){
		initWs(urls, event);
	}, 10000);
}



/**
 * 心跳机制
 */
var time;
function heart(){
	time = setTimeout(function(){
        time = null;

		heart();
		//发送一个心跳消息
		console.log("发送心跳消息...{\"id\":\"ack\",\"name\":\"1\"}");
		sendMsg("{\"id\":\"ack\",\"name\":\"1\"}");
// 		sendObjMsg(wsmsg);
	}, 5000);
}

/**
 * 关闭连接
 */
var closeTime;
function closeConn(){
    closeTime = setTimeout(function(){
        closeTime = null;
    	//关闭连接
		if(websocket){
			console.log("主动关闭连接...")
            websocket.close();
		}
    }, 1000 * 30);
}
