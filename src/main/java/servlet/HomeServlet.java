package servlet;

import pojo.Article;
import pojo.User;
import util.DBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
                    List<Article> articles = new ArrayList<>();
                    PreparedStatement ps = conn.prepareStatement("SELECT id, title, time FROM article WHERE username = ?");
                    ps.setString(1, username);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        Article a = new Article();
                        Integer id = rs.getInt("id");
                        String title = rs.getString("title");
                        Date date = rs.getDate("time");
                        a.setId(id);
                        a.setTitle(title);
                        a.setTime(date);
                        articles.add(a);
                    }
                    // 将User对象设置为请求属性
                    req.setAttribute("articles", articles);
                    req.setAttribute("user", user);
                    // 转发请求到JSP页面
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/users/home.jsp");
                    dispatcher.forward(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            } else {
                resp.getWriter().write("用户名为空");
            }
        } else {
            resp.getWriter().write("会话不存在");
        }
    }
}
