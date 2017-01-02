/**
 * Created by fuqingjian on 2016/12/12.
 */

var webSocket =
    new WebSocket(localStorage.getItem("websocketPath")+'/rtswebsocket');

webSocket.onerror = function(event) {
    onError(event)
};

webSocket.onopen = function(event) {
    //当type为1的时候，messageinfo为登陆人账号
    var userid=localStorage.getItem("userid");
    webSocket.send("{type:1,Messageinfo:"+userid+"}");
};
//接受分数
webSocket.onmessage = function(event) {
    var data=event.data;
    document.getElementById("betpoints").innerHTML=data;
};
