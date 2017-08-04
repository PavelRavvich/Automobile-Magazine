package ru.pravvich.servlet;

import org.junit.Test;
import ru.pravvich.dao.DAOImpl;
import ru.pravvich.model.Propose;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SelectProposeServletTest {

    @Test
    public void whenDoGet() throws ServletException, IOException {

        final HttpServletResponse resp = mock(HttpServletResponse.class);
        final HttpServletRequest req = mock(HttpServletRequest.class);

        when(req.getParameter("mark")).thenReturn("mark");
        when(req.getParameter("model")).thenReturn("model");


        final ServletContext context = mock(ServletContext.class);
        when(req.getServletContext()).thenReturn(context);

        final DAOImpl dao = mock(DAOImpl.class);
        when(context.getAttribute("dao")).thenReturn(dao);


        final List<Propose> select = new ArrayList<>();
        select.add(new Propose());
        when(dao.selectBy("mark","model")).thenReturn(select);


        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/view/all_proposes.jsp"))
                .thenReturn(dispatcher);


        new SelectProposeServlet().doGet(req, resp);


        verify(req).setAttribute("allProposes", select);
        verify(req).getRequestDispatcher("/WEB-INF/view/all_proposes.jsp");
        verify(dispatcher).forward(req, resp);
    }
}