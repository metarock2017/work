<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/17
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.lang.Math" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="org.redrock.util.DatabaseUtil" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<html>
<head>
    <title>谁是卧底</title>
</head>
<body>
<%
    String FromUserName = request.getParameter("openner");
    String url = "/WordServlet?openner="+FromUserName;
    int x = 1+(int)(Math.random()*216);
    Connection conn = DatabaseUtil.getConnection();
    PreparedStatement pstmt = DatabaseUtil.preparedStmt(conn, "select * from words where id=?");
    pstmt.setInt(1, x);
    ResultSet rs = DatabaseUtil.executeQuery(pstmt);
%>
    <form action="<%=url%>" method="post">
        <select name="total_num"><br>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
            <option value="13">13</option>
        </select>
        <table>
            <b>游戏出题</b><br>
            <tr>
                <td>卧底词：</td>
                <%try{
                    if (rs.next()){%>
                <td><input type="text" name="wodici" value="<%=rs.getString("word_one")%>"></td>
            </tr>
            <tr>
                <td>平民词：</td>
                <td><input type="text" name="pingminci" value="<%=rs.getString("word_two")%>"></td>
                <%  }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                %>
            </tr>
        </table>
        <input type="submit" value="创建游戏">
    </form>
<%--<%--%>
    <%--DatabaseUtil.close(rs);--%>
    <%--DatabaseUtil.close(pstmt);--%>
    <%--DatabaseUtil.close(conn);--%>
<%--%>--%>
</body>
</html>
