package core.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import next.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestDispatcher dispatcher;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getRequestURI().substring(req.getContextPath().length());

        String result = null;
        try {
            result = RequestMapping.getController(path).execute(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.info("result : " + result);
        if (result.startsWith("redirect:")) {
            result = result.substring("redirect:".length());
            logger.info("redirect "+ " "+ result);
            resp.sendRedirect(result);

            return ;
        }
        dispatcher = req.getRequestDispatcher(result);
        dispatcher.forward(req, resp);
    }
}
