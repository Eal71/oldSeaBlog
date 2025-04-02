package servlet;

import pojo.User;
import util.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String articleTitle = req.getParameter("articleTitle");
        String articleContent = req.getParameter("articleContent");
        String articleSummary = req.getParameter("articleSummary");
        //创建一个User对象
        User user = new User();
        //获取session，如果不存在则不创建新的 session
        HttpSession session = req.getSession(false);
        if (session != null) {
            //从session中获取username
            String username = (String) session.getAttribute("user");
            if (username != null) {
                //将username存入User实体类中
                user.setUsername(username);
                try (Connection conn = DBUtil.getConnection()) {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO article (title,text,summary,time,username) VALUES (?, ?, ?, now(),?)");
                    ps.setString(1, articleTitle);
                    ps.setString(2, articleContent);
                    ps.setString(3, articleSummary);
                    ps.setString(4, username);
                    int affectedRows = ps.executeUpdate();
                    if (affectedRows > 0) {
                        // 成功插入记录后重定向或显示成功消息
                        resp.sendRedirect(req.getContextPath() + "/home");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                resp.getWriter().write("用户名为空");
            }
        } else {
            resp.getWriter().write("会话不存在");
        }
    }
}

