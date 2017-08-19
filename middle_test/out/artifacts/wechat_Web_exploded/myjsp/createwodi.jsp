<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/17
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>谁是卧底</title>
</head>
<body>
    <form action="x" method="post">
        <select name="people" onchange="change(this.value)">
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
            <tr>
                <td>卧底 X <span id="wodinum">1</span></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <td>平民 X <span id="pingminnum">3</span></td>
            </tr>
        </table>
        <table>
            <b>游戏出题</b><br>
            <input type="button" onclick="doRequestUsingGet()" value="随机换题" />
            <tr>
                <td>卧底词：</td>
                <td><input type="text" name="wodi" value="被子"></td>
            </tr>
            <tr>
                <td>平民词：</td>
                <td><input type="text" name="pingmin" value="毛毯"></td>
            </tr>
        </table>
        <input type="submit" value="创建游戏">
    </form>
</body>
</html>
