package ru.pravvich.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.pravvich.dao.DAO;
import ru.pravvich.model.Propose;
import ru.pravvich.model.User;
import ru.pravvich.util.FileReceiver;
import ru.pravvich.util.TextReceiver;
import ru.pravvich.util.Uploader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Servlet for parse request and addition propose to database.
 */
public class AddProposeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Current
        final ServletConfig config = this.getServletConfig();

        final ServletFileUpload upload = new Uploader().get(config);

        // Set overall request size constraint
        upload.setSizeMax(10_000_000);

        // Parse the request
        try {
            final List<FileItem> items = upload.parseRequest(req);

            final byte[] image = new FileReceiver().receive(items);

            final Map<String, String> param = new TextReceiver().receive(items);

            final DAO dao = (DAO) getServletContext().getAttribute("dao");

            final Propose propose = new Propose(
                    new User(getAuthorId(req)),
                    param.get("mark"),
                    param.get("model"),
                    param.get("description"),
                    image);

            dao.addPropose(propose);

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Auhtor id from request.
     */
    private int getAuthorId(HttpServletRequest req) {
        final HttpSession session = req.getSession(false);
        return (int) session.getAttribute("id");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/add_propose.jsp")
                .forward(req, resp);
    }
}
