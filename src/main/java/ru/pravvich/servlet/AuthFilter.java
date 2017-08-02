package ru.pravvich.servlet;

import ru.pravvich.dao.DAO;
import ru.pravvich.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * Auntification filter.
 */
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

        } else {

            path = "/index.jsp";
        }

        req.getRequestDispatcher(path).forward(req, resp);
    }

    /**
     * Check exist user in database by login and password.
     * If user is exist call this.createSession(HttpServletRequest, User)
     *
     * @param req current request.
     * @return true - exist, false not exist.
     */
    private boolean userIsRegisteredDB(final HttpServletRequest req) {

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        if (login == null || password == null) return false;

        final DAO dao = (DAO) req.getServletContext().getAttribute("dao");

        final User user = dao.getUser(login, password);

        if (user.getId() != 0) createSession(req, user);

        return user.getId() != 0;
    }

    /**
     * Create HttpSession for current user.
     * Inject in session id, login, password of user.
     *
     * @param req current request.
     * @param user current user.
     */
    private void createSession(final HttpServletRequest req, final User user) {

        final HttpSession session = req.getSession();

        session.setAttribute("id", user.getId());
        session.setAttribute("login", user.getLogin());
        session.setAttribute("password", user.getPassword());
    }

    /**
     * Check exist login and password in session.
     *
     * @param session current request.
     * @return true if exist, else - false.
     */
    private boolean sessionIsExist(final HttpSession session) {

        final String loginSes = (String) session.getAttribute("login");
        final String passSes = (String) session.getAttribute("password");

        return nonNull(loginSes) && nonNull(passSes);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
