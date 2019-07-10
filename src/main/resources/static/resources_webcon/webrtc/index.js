/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

//var ws = new WebSocket('ws://172.10.4.182:9999');
var ws;
var videoInput;
var videoOutput;
var webRtcPeer;
var response;
var callerMessage;
var from;

var registerName = null;
var registerState = null;
var NOT_REGISTERED = 0;
var REGISTERING = 1;
var REGISTERED = 2;

var data;

var ws_state = false;
var users;

function initWsRtc(dataInfo){

    if(gloabStatus){
        //则断开连接
        ws.close();
        ws_state = false;
    }

    gloabStatus = true;

    data = dataInfo;
    ws = new WebSocket(data.url);

    ws.onopen = function()
    {
        console.log('open websocket OK');
        ws_state = true;

        //TODO 注册websocket
        ws.send("{\"id\":\"register\",\"name\":\"" + data.name + "\"}");
    };

    ws.onclose = function(){
        ws_state = false;
    };

    ws.onmessage = function(message) {
        var parsedMessage = JSON.parse(message.data);
        console.info('Received message: ' + message.data);

        switch (parsedMessage.id) {
            case 'resgisterResponse':
                resgisterResponse(parsedMessage);
                break;
            case 'viewerResponse':
                callResponse(parsedMessage);
                break;
            case 'incomingCall':
                incomingCall(parsedMessage);
                break;
            case 'startCommunication':
                startCommunication(parsedMessage);
                break;
            case 'stopCommunication':
                console.info('Communication ended by remote peer');
                stop(true);
                break;
            case 'iceCandidate':
                webRtcPeer.addIceCandidate(parsedMessage.candidate, function(error) {
                    if (error)
                        return console.error('Error adding candidate: ' + error);
                });
                break;
            case 'creato2mroom_rpy':
                if(parsedMessage.response == "success"){
                    //发送消息
                    //TODO 发送其他终端接入消息
                    // alert("发送给其他终端：{\"meetname\":\"meeting\",\"users\":" + JSON.stringify(users) + ",\"num\":4,\"isVideo\":true,\"room\":\"" + data.name + "\",\"id\":\"noticeJoinO2MRoom\"}");
                    ws.send("{\"meetname\":\"meeting\",\"users\":" + JSON.stringify(users) + ",\"num\":4,\"isVideo\":true,\"room\":\"" + data.name + "\",\"id\":\"noticeJoinO2MRoom\"}");
                }
                break;
            case 'startResponse':
                // TODO 处理管道联通响应的
                webRtcPeer.processAnswer(parsedMessage.sdpAnswer, function(error) {
                    if (error)
                        return console.error(error);
                });
                break;
            default:
                console.error('Unrecognized message', parsedMessage);
        }
    }
}


function setRegisterState(nextState) {
    switch (nextState) {
        case NOT_REGISTERED:
            enableButton('#register', 'register()');
            setCallState(NO_CALL);
            break;
        case REGISTERING:
            disableButton('#register');
            break;
        case REGISTERED:
            disableButton('#register');
            setCallState(NO_CALL);
            break;
        default:
            return;
    }
    registerState = nextState;
}

var callState = null;
var NO_CALL = 0;
var PROCESSING_CALL = 1;
var IN_CALL = 2;

function setCallState(nextState) {
    switch (nextState) {
        case NO_CALL:
            enableButton('#call', 'call()');
            disableButton('#terminate');
            disableButton('#play');
            break;
        case PROCESSING_CALL:
            disableButton('#call');
            disableButton('#terminate');
            disableButton('#play');
            break;
        case IN_CALL:
            disableButton('#call');
            enableButton('#terminate', 'stop()');
            disableButton('#play');
            break;
        default:
            return;
    }
    callState = nextState;
}

$(function(){
    console = new Console();
    setRegisterState(NOT_REGISTERED);
    // var drag = new Draggabilly(document.getElementById('videoSmall'));
    videoInput = document.getElementById('videoInput');
    videoOutput = document.getElementById('videoOutput');
    // document.getElementById('name').focus();
});

/*window.onload = function() {
    console = new Console();
    setRegisterState(NOT_REGISTERED);
    var drag = new Draggabilly(document.getElementById('videoSmall'));
    videoInput = document.getElementById('videoInput');
    videoOutput = document.getElementById('videoOutput');
    document.getElementById('name').focus();
}*/

window.onbeforeunload = function() {
    ws.close();
}

function reConnectWs(){
    console.log("开始重连......" + data.url)
    ws.close();
    // ws = new WebSocket('ws://' + location.host + '/call');
    ws = new WebSocket(data.url);
    setRegisterState(NOT_REGISTERED);
}



function resgisterResponse(message) {
    if (message.response == 'accepted') {
        setRegisterState(REGISTERED);
    } else {
        setRegisterState(NOT_REGISTERED);
        var errorMessage = message.message ? message.message
            : 'Unknown reason for register rejection.';
        console.log(errorMessage);
        alert('Error registering user. See console for further information.');
    }
}

function callResponse(message) {
    /*if (message.response != 'accepted') {
        console.info('Call not accepted by peer. Closing call');
        var errorMessage = message.message ? message.message
                : 'Unknown reason for call rejection.';
        console.log(errorMessage);
        stop();
    } else*/ {
        setCallState(IN_CALL);
        webRtcPeer.processAnswer(message.sdpAnswer, function(error) {
            if (error)
                return console.error(error);
        });
    }
}

function startCommunication(message) {
    setCallState(IN_CALL);
    webRtcPeer.processAnswer(message.sdpAnswer, function(error) {
        if (error)
            return console.error(error);
    });
}

function incomingCall(message) {
    // If bussy just reject without disturbing user
    if (callState != NO_CALL) {
        var response = {
            id : 'incomingCallResponse',
            from : message.from,
            callResponse : 'reject',
            message : 'bussy'
        };
        return sendMessage(response);
    }

    setCallState(PROCESSING_CALL);
    if (confirm('User ' + message.from
        + ' is calling you. Do you accept the call?')) {
        showSpinner(videoInput, videoOutput);

        from = message.from;
        var options = {
            localVideo : videoInput,
            remoteVideo : videoOutput,
            onicecandidate : onIceCandidate,
            onerror : onError
        }
        webRtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendrecv(options,
            function(error) {
                if (error) {
                    return console.error(error);
                }
                webRtcPeer.generateOffer(onOfferIncomingCall);
            });

    } else {
        var response = {
            id : 'incomingCallResponse',
            from : message.from,
            callResponse : 'reject',
            message : 'user declined'
        };
        sendMessage(response);
        stop();
    }
}

function onOfferIncomingCall(error, offerSdp) {
    if (error)
        return console.error("Error generating the offer");
    var response = {
        id : 'incomingCallResponse',
        from : from,
        callResponse : 'accept',
        sdpOffer : offerSdp
    };
    sendMessage(response);
}

// function register() {
//     var name = document.getElementById('name').value;
//     if (name == '') {
//         window.alert('You must insert your user name');
//         return;
//     }
//     setRegisterState(REGISTERING);
//
//     var message = {
//         id : 'register',
//         name : name
//     };
//     sendMessage(message);
//     document.getElementById('peer').focus();
// }

function call(us) {
    // if (document.getElementById('peer').value == '') {
    //     window.alert('You must specify the peer name');
    //     return;
    // }

    //设置当前终端列表
    users = us;

    if(!ws_state){
        alert("服务器连接异常，无法进行寻呼！");
        return;
    }

    setCallState(PROCESSING_CALL);
    showSpinner(videoInput, videoOutput);

    var options = {
        localVideo : videoInput,
        remoteVideo : videoOutput,
        onicecandidate : onIceCandidate,
        onerror : onError
    }
    webRtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendrecv(options,
        function(error) {
            if (error) {
                return console.error(error);
            }
            webRtcPeer.generateOffer(onOfferCall);
        }
    );
}

//-------------备份-----------------------
// function onOfferCall(error, offerSdp) {
//     if (error)
//         return console.error('Error generating the offer');
//     console.log('Invoking SDP offer callback function');
//     var message = {
//         id : 'callLive',
//         from : document.getElementById('name').value,
//         to : document.getElementById('peer').value,
//         sdpOffer : offerSdp
//     };
//     sendMessage(message);
// }

function onOfferCall(error, offerSdp) {
    if (error)
        return console.error('Error generating the offer');
    console.log('Invoking SDP offer callback function');
    var message = {
        id : 'startLive',
        meetname : 'meeting',
        sdpOffer : offerSdp
    };
    sendMessage(message);
}

function stop(message) {
    setCallState(NO_CALL);
    if (webRtcPeer) {
        webRtcPeer.dispose();
        webRtcPeer = null;

        if (!message) {
            var message = {
                id : 'stop'
            }
            sendMessage(message);
        }
    }
    hideSpinner(videoInput, videoOutput);
}

function onError() {
    setCallState(NO_CALL);
}

function onIceCandidate(candidate) {
    console.log("Local candidate" + JSON.stringify(candidate));

    var message = {
        id : 'onIceCandidate',
        candidate : candidate
    };
    sendMessage(message);
}

function sendMessage(message) {
    if(!ws_state){
        console.log('ws_socket not open');
        reConnectWs();
    }
    var jsonMessage = JSON.stringify(message);
    console.log('Senging message: ' + jsonMessage);
    ws.send(jsonMessage);
}

function showSpinner() {
    // for (var i = 0; i < arguments.length; i++) {
    //     arguments[i].poster = './img/transparent-1px.png';
    //     arguments[i].style.background = 'center transparent url("./img/spinner.gif") no-repeat';
    // }
}

function hideSpinner() {
    for (var i = 0; i < arguments.length; i++) {
        // arguments[i].src = '';
        // arguments[i].poster = './img/webrtc.png';
        // arguments[i].style.background = '';
    }
}

function disableButton(id) {
    $(id).attr('disabled', true);
    $(id).removeAttr('onclick');
}

function enableButton(id, functionName) {
    $(id).attr('disabled', false);
    $(id).attr('onclick', functionName);
}

/**
 * Lightbox utility (to display media pipeline image in a modal dialog)
 */
$(document).delegate('*[data-toggle="lightbox"]', 'click', function(event) {
    event.preventDefault();
    $(this).ekkoLightbox();
});
