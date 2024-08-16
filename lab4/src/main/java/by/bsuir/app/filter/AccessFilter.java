package by.bsuir.app.filter;

import by.bsuir.app.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String parameter = servletRequest.getParameter("command");
        if (parameter != null) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
            Role role = (Role) session.getAttribute("role");
            switch (parameter) {
                case "showRooms":
                case "addRoom":
                case "deoccupyRoom":
                    if (Role.USER.equals(role)) {
                        ((HttpServletResponse) servletResponse).sendError(403);
                        return;
                    }
                    break;
                case "mainPage":
                case "makeOrder":
                    if (Role.ADMIN.equals(role)) {
                        ((HttpServletResponse) servletResponse).sendError(403);
                        return;
                    }
                    break;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
