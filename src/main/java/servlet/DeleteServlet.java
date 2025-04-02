package servlet;

import pojo.User;
import util.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
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
                String idParam = req.getParameter("id");
                int idd = Integer.parseInt(idParam);
                try (Connection conn = DBUtil.getConnection()) {
                    PreparedStatement ps1 = conn.prepareStatement("DELETE FROM article WHERE id = ? AND username = ?");
                    ps1.setInt(1, idd);
                    ps1.setString(2, username);
                    int rowsAffected = ps1.executeUpdate();
                    if (rowsAffected > 0) {
                        resp.sendRedirect("/home");
                    } else {
                        resp.getWriter().write("未找到指定的文章或无权限删除");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}