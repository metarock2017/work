package mession1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = "登陆成功";
        UserDo userDo = new UserDo();
        PrintWriter out = response.getWriter();
        if(userDo.isEmpty(username)) {
            message = "用户名不能为空";
        } else if (userDo.isEmpty(password)) {
            message = "密码不能为空";
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            boolean re = userDo.login(user);
            if (!re){
                message = "用户名或密码错误";
            }else {
                message = "登陆成功";
            }
        }

        out.println("<script type='text/javascript'>alert("+message+")</script>");
    }
}
