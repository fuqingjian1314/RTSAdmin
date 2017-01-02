<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/16
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Testing websockets</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
</head>
<body>
<div>
    <input type="submit" value="Start" onclick="start()" />
    <input type="button" value="Send" onclick="sendinfo()">
    <input type="button" value="后台Send" onclick="send()">
</div>
<div id="messages"></div>
<script type="text/javascript">
    var webSocket =
            new WebSocket('ws://localhost:8080/RTSAdmin/rtswebsocket');

    webSocket.onerror = function(event) {
        onError(event)
    };

    webSocket.onopen = function(event) {
        onOpen(event)
    };

    webSocket.onmessage = function(event) {
        onMessage(event)
    };

    function onMessage(event) {
        document.getElementById('messages').innerHTML
                += '<br />' + event.data;
    }

    function onOpen(event) {
        document.getElementById('messages').innerHTML
                = 'Connection established';
    }

    function onError(event) {
        alert(event.data);
    }

    function start() {

        webSocket.send("{type:1,Messageinfo:'abcde'}");
        return false;
    }
    function sendinfo() {
        webSocket.send("{type:2,Messageinfo:'abcde开始发送消息'}");
        return false;
    }
    function send(){
        $.post('${pageContext.request.contextPath}/wsocket/sd.shtml', {username:"abcde"}, function (text, status) {  });
    }


</script>
</body>
</html>
