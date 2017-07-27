package ru.pravvich.servlet;

import ru.pravvich.dao.DAO;
import ru.pravvich.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        final HttpSession session = req.getSession(false);

        String path;

        if (session != null && sessionIsExist(session)) {

            path = req.getServletPath();

            if (path.contains("index.jsp")) path = "/get_all_proposes";

        } else if (userIsRegisteredDB(req)) {

            path = "/get_all_proposes";

            createSession(req);

        } else {

            // If user not exist in DB and not have a session go to sign in.
            path = "/index.jsp";
        }

        req.getRequestDispatcher(path).forward(req, resp);
    }

    private boolean userIsRegisteredDB(final HttpServletRequest req) {

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        if (login==null || password == null) return false;

        final DAO dao = (DAO) req.getServletContext().getAttribute("dao");

        final User user = dao.getUser(login, password);

        return user != null;
    }

    private void createSession(final HttpServletRequest req) {

        final HttpSession session = req.getSession();

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        session.setAttribute("login", login);
        session.setAttribute("password", password);
    }


    private boolean sessionIsExist(final HttpSession session) {

        final String loginSes = (String) session.getAttribute("login");
        final String passSes = (String) session.getAttribute("password");

        return nonNull(loginSes) && nonNull(passSes);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
