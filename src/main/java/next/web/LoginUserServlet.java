package next.web;

import core.db.DataBase;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CreateUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = DataBase.findUserById(userId);
        RequestDispatcher rd;
        HttpSession session = req.getSession();
        if(user.getUserId().equals(userId) && user.getPassword().equals(password)) {

            session.setAttribute("user", user);
        }
        rd = req.getRequestDispatcher("/user/login.jsp");
        rd.forward(req, resp);
    }
}
