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
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection conn = DBUtil.getConnection()) {
            // 查询用户名
            PreparedStatement ps = conn.prepareStatement("SELECT username FROM user LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                // 创建一个User对象
                User user = new User();
                user.setUsername(username);

                // 查询昵称
                PreparedStatement ps0 = conn.prepareStatement("SELECT nickname FROM user WHERE username = ?");
                ps0.setString(1, username);
                ResultSet rs0 = ps0.executeQuery();
                if (rs0.next()) {
                    String nickname = rs0.getString("nickname");
                    user.setNickname(nickname);
                } else {
                    System.out.println("查询昵称失败");
                }

                // 查询文章数量
                PreparedStatement ps1 = conn.prepareStatement("SELECT count(*) FROM article WHERE username = ?");
                ps1.setString(1, username);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    int articleNumber = rs1.getInt(1);
                    user.setArticleNumber(articleNumber);
                } else {
                    System.out.println("查询文章数量失败");
                }

                // 查询头像
                PreparedStatement ps3 = conn.prepareStatement("SELECT image FROM user WHERE username = ?");
                ps3.setString(1, username);
                ResultSet rs3 = ps3.executeQuery();
                if (rs3.next()) {
                    String image = rs3.getString("image");
                    user.setImage(image);
                } else {
                    System.out.println("查询头像失败");
                }

                // 查询文章列表
                List<Article> articles = new ArrayList<>();
                PreparedStatement ps4 = conn.prepareStatement("SELECT id, title, summary, time FROM article WHERE username = ?");
                ps4.setString(1, username);
                ResultSet rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    Article a = new Article();
                    a.setId(rs4.getInt("id"));
                    a.setTitle(rs4.getString("title"));
                    a.setSummary(rs4.getString("summary"));
                    a.setTime(rs4.getDate("time"));
                    articles.add(a);
                }

                // 将User对象设置为请求属性
                req.setAttribute("articles", articles);
                req.setAttribute("user", user);

                // 转发请求到JSP页面
                RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/index.jsp");
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





