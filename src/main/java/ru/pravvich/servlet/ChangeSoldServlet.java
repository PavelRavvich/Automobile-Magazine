package ru.pravvich.servlet;

import ru.pravvich.dao.DAO;
import ru.pravvich.model.Propose;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class ChangeSoldServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final int auhtorId = parseInt(req.getParameter("aId"));

        final int current = (int) req.getSession().getAttribute("id");


        if (current == auhtorId) changeSoldStatus(req);

        req.getRequestDispatcher("/get_all_proposes")
                .forward(req, resp);
    }

    private void changeSoldStatus(HttpServletRequest req) {

        final DAO dao = (DAO) getServletContext().getAttribute("dao");

        final int proposeId = parseInt(req.getParameter("pId"));

        final Propose propose = dao.getProposeById(proposeId);

        propose.setSold(true);

        dao.update(propose);
    }
}
