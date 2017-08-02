package ru.pravvich.servlet;

import com.google.gson.Gson;
import ru.pravvich.dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Send to jsp data select of models set by unique mark value.
 */
public class GetModelByMarkServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final DAO dao = (DAO) getServletContext().getAttribute("dao");

        final String mark = req.getParameter("mark");

        final List<String> models = dao.getModelsByMark(mark);

        final String jsonModel = new Gson().toJson(models);

        resp.getWriter().write(jsonModel);
    }
}
