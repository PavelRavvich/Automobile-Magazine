package ru.pravvich.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.pravvich.util.FileReceiver;
import ru.pravvich.util.TextReceiver;
import ru.pravvich.util.UploadFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class AddProposeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        final ServletConfig config = this.getServletConfig();

        final ServletFileUpload upload = new UploadFactory().get(config);

        // Set overall request size constraint
        upload.setSizeMax(10_000_000);

        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(req);

            File file = new File("/Users/MyMac/Desktop/im.jpg");

            final byte[] receive = new FileReceiver().receive(items);

            final Map<String, String> text = new TextReceiver().receive(items);

            for (String s : text.values()) System.out.println(s);

            Files.write(file.toPath(), receive);

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/add_propose.jsp")
                .forward(req, resp);
    }
}
