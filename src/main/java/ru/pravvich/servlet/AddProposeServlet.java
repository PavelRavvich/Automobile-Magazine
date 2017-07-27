package ru.pravvich.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddProposeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final String mark = req.getParameter("mark");
        final String model = req.getParameter("model");
        final String description = req.getParameter("description");


        boolean isMultipart = ServletFileUpload.isMultipartContent(req);


        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        List<Byte> image = new ArrayList<>();

        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                final byte[] bytes = item.get();
                for (byte b : bytes) {
                    image.add(b);
                }
            }


            File file = new File("/Users/MyMac/Desktop/im.jpg");
            final boolean success = file.createNewFile();
            Byte[] res = new Byte[image.size()];
            final Byte[] bytes = image.toArray(res);
            byte[] resu = new byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                resu[i] = bytes[i];
            }
            Files.write(file.toPath(), resu);

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        System.out.println(isMultipart);
        System.out.println("!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/add_propose.jsp")
                .forward(req, resp);
    }
}
