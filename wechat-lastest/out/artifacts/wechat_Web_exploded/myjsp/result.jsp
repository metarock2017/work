<%@ page import="org.redrock.play.Room" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/20
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>建房成功</title>
</head>
<body>
<%Room room = (Room) request.getAttribute("room");%>
<% Set<Integer> set = (Set<Integer>) request.getAttribute("set"); %>
<div>房间号：<%=room.getRoom_num()%></div><br>
<div>
    <div>配置：<%=room.getWodi_num()%>卧底，<%=room.getPingmin_num()%>平民</div>
    <div>卧底词：	<%=room.getWodici()%></div>
    <div>平民词：<%=room.getPingminci()%></div>
    <div>卧底：
    <%
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            Integer wodi = it.next();
            %>
    <%=wodi%>号&nbsp;
    <%
        }
    %>
    </div>
</div><br>
<div>
    回复房间号，可以再次查看房间信息<br>
    new 回复6，查看大冒险惩罚！<br>
    如果有玩家未关注桌游助手，请将微信号分享给你的好友！<br>
</div>
</body>
</html>
