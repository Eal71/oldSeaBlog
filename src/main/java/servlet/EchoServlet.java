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


@WebServlet("/echo")
public class EchoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        // 获取文章ID
        String articleIdStr = req.getParameter("id");
        int articleId = Integer.parseInt(articleIdStr);
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
                    Article article = new Article();
                    PreparedStatement ps = conn.prepareStatement("SELECT title, text, summary FROM article WHERE username = ? and id = ?");
                    ps.setString(1, username);
                    ps.setInt(2, articleId);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String title = rs.getString("title");
                        String text = rs.getString("text");
                        String summary = rs.getString("summary");
                        article.setTitle(title);
                        article.setText(text);
                        article.setSummary(summary);
                        article.setId(articleId);
                    }
                    req.setAttribute("article", article);
                    // 转发请求到JSP页面
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/article/edit.jsp");
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
