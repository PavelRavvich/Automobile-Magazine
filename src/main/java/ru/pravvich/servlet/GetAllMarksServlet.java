package ru.pravvich.servlet;

import com.google.gson.Gson;
import ru.pravvich.dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllMarksServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final DAO dao = (DAO) getServletContext().getAttribute("dao");

        final List<String> mark = dao.getMarks();

        final String jsonMark = new Gson().toJson(mark);

        resp.getWriter().write(jsonMark);

    }
}
