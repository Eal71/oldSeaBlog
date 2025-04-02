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

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        int id = Integer.parseInt(idParam);
        try (Connection conn = DBUtil.getConnection()) {
            // 查询用户名
            PreparedStatement ps = conn.prepareStatement("SELECT username FROM user LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                // 创建一个User对象
                User user = new User();
                user.setUsername(username);

                //查询昵称
                PreparedStatement ps0 = conn.prepareStatement("SELECT nickname FROM user WHERE username = ?");
                ps0.setString(1, username);
                ResultSet rs0 = ps0.executeQuery();
                if (rs0.next()) {
                    String nickname = rs0.getString("nickname");
                    user.setNickname(nickname);
                } else {
                    System.out.println("查询失败");
                }

                PreparedStatement ps1 = conn.prepareStatement("SELECT count(*) FROM article WHERE username = ?");
                ps1.setString(1, username);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    int articleNumber = rs1.getInt(1);
                    user.setArticleNumber(articleNumber);
                } else {
                    System.out.println("查询失败");
                }

                PreparedStatement ps3 = conn.prepareStatement("SELECT image FROM user WHERE username = ?");
                ps3.setString(1, username);
                ResultSet rs3 = ps3.executeQuery();
                if (rs3.next()) {
                    String image = rs3.getString("image");
                    user.setImage(image);
                } else {
                    System.out.println("查询失败");
                }

                PreparedStatement ps4 = conn.prepareStatement("SELECT title, text, time FROM article WHERE username = ? and id = ?");
                ps4.setString(1, username);
                ps4.setInt(2, id);
                ResultSet rs4 = ps4.executeQuery();

                Article article = new Article();
                if (rs4.next()) {
                    String title = rs4.getString("title");
                    String text = rs4.getString("text");
                    Date date = rs4.getDate("time");
                    article.setTitle(title);
                    article.setText(text);
                    article.setTime(date);
                }
                // 将User对象设置为请求属性
                req.setAttribute("article", article);
                req.setAttribute("user", user);
                // 转发请求到JSP页面
                RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/article/detail.jsp");
                dispatcher.forward(req, resp);
            } else {
                System.out.println("查询用户名失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}

