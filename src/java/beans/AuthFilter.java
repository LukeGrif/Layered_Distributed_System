package beans;

import entities.Admin;
import entities.Freelancer;
import entities.Provider;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*.xhtml")
public class AuthFilter implements Filter {

    @Inject
    private LoginBean loginBean;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        // Role-based restriction
        if (uri.contains("/admin/") && !(loginBean.getLoggedInUser() instanceof Admin)) {
            res.sendRedirect(req.getContextPath() + "/unauthorised.xhtml");
            return;
        } else if (uri.contains("/provider/") && !(loginBean.getLoggedInUser() instanceof Provider)) {
            res.sendRedirect(req.getContextPath() + "/unauthorised.xhtml");
            return;
        } else if (uri.contains("/freelancer/") && !(loginBean.getLoggedInUser() instanceof Freelancer)) {
            res.sendRedirect(req.getContextPath() + "/unauthorised.xhtml");
            return;
        }

        // All good
        chain.doFilter(request, response);
    }
}
