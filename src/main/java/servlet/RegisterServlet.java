package servlet;

import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/register")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "E:\\code\\oldSeaBlog\\web\\images\\"; // 指定上传目录
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        //获取表单中的用户名和密码和昵称
        String username = req.getParameter("username");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        //获取文件part对象
        Part part = req.getPart("file");
        //获取文件名
        String filename = part.getSubmittedFileName();
        String filePath = UPLOAD_DIRECTORY + filename;
        // 创建目标文件
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        // 保存文件
        part.write(filePath);

        try (Connection conn = DBUtil.getConnection())//数据库连接错误，将mysql-connector-java-8.0.28.jar放入java1.8\jre\lib\ext
        {
            // 检查用户名是否存在
            PreparedStatement psCheck = conn.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ?");
            psCheck.setString(1, username);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                resp.sendRedirect("pages/users/register.jsp?error=3"); // 用户名已存在
                return;
            }
            //插入数据库
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user (username, password,nickname,image) VALUES (?, ?,?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, nickname);
            ps.setString(4, filename);

            int rowsAffected = ps.executeUpdate();//插入数据
            if (rowsAffected > 0) {
                resp.sendRedirect("pages/users/login.jsp");// 注册成功跳转
            } else {
                resp.sendRedirect("pages/users/register.jsp?error=1"); // 注册失败跳转
            }

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("pages/users/register.jsp?error=2");
        }
    }

}
