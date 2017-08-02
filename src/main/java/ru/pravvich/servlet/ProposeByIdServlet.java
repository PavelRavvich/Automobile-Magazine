package ru.pravvich.servlet;

import ru.pravvich.dao.DAO;
import ru.pravvich.model.Propose;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProposeByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final int pId = Integer.valueOf(req.getParameter("pId"));

        final DAO dao = (DAO) getServletContext().getAttribute("dao");

        final Propose propose = dao.getProposeById(pId);

        req.setAttribute("propose", propose);

        req.getRequestDispatcher("/WEB-INF/view/single_propose.jsp")
                .forward(req, resp);

    }
}
