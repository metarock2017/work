package mession1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String message = "注册成功";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        UserDo userdo = new UserDo();

        if (userdo.isEmpty(username)) {
            message = "用户不能为空";
        } else if(userdo.isEmpty(password)) {
            message = "密码不能为空";
        } else if (userdo.isEmpty(question)) {
            message = "问题不能为空";
        } else if (userdo.isEmpty(answer)) {
            message = "答案不能为空";
        } else if (!userdo.okUsername(username)) {
            message = "用户名必须为纯英文且长度不超过10个英文字母";

        } else if (!userdo.okPassword(password)) {
            message = "密码必须为6位纯数字";
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(Base64.getBase64(password));
            user.setQuestion(question);
            user.setAnswer(answer);
            boolean re = userdo.regsiter(user);
            if (!re) {
                message = "用户名已存在";
            } else {

            }

        }
        out.println("<script type='text/javascript'>alert("+message+")</script>");
    }
}
