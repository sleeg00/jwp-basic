package next.model;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspView implements View {
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    private String url;

    public JspView(String url) {
        if(url == null) {
            throw new NullPointerException("view is null");
        }
        this.url = url;
    }

    @Override
    public Void render(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (this.url.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            resp.sendRedirect(this.url.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return null;
        }

        RequestDispatcher rd = req.getRequestDispatcher(this.url);
        rd.forward(req, resp);
    }
}
