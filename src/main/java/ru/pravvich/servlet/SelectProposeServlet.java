package ru.pravvich.servlet;

import ru.pravvich.dao.DAO;
import ru.pravvich.model.Propose;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SelectProposeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        final DAO dao = (DAO) req.getServletContext().getAttribute("dao");

        final String mark = req.getParameter("mark");

        final String model = req.getParameter("model");

        final List<Propose> select = dao.select(mark, model);

        req.setAttribute("allProposes", select);

        req.getRequestDispatcher("/WEB-INF/view/all_proposes.jsp")
                .forward(req, resp);
    }
}
