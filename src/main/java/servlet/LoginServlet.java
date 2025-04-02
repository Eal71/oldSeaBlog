package servlet;


import util.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取表单中的用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //数据库查询
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // 登录成功
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                if(Objects.equals(username1, username) && Objects.equals(password1, password)){
                    HttpSession session = req.getSession();
                    session.setAttribute("user", username1);  // 将用户名存入session
                    resp.sendRedirect("/home");
                }
                else {
                    // 登录失败
                    resp.sendRedirect("pages/users/login.jsp?error=1");  // 重定向回登录页面，并附带错误信息
                }
            }
            else {
                // 登录失败
                resp.sendRedirect("pages/users/login.jsp?error=1");  // 重定向回登录页面，并附带错误信息
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("pages/users/login.jsp?error=2");  // 数据库操作出错
        }
    }
}
