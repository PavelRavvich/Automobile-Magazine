package ru.pravvich.servlet;

import ru.pravvich.dao.DAO;
import ru.pravvich.model.Propose;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllProposesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        final DAO dao = (DAO) getServletContext().getAttribute("dao");

        final List<Propose> allProposes = dao.getAllProposes();

        req.setAttribute("allProposes", allProposes);

        req.getRequestDispatcher("/WEB-INF/view/all_proposes.jsp")
                .forward(req, resp);
    }
}
