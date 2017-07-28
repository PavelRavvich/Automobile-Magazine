package ru.pravvich.util;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.io.File;

public class UploadFactory {

    private static final String tmp = "javax.servlet.context.tempdir";

    public ServletFileUpload get(final ServletConfig config) {

        // Create a factory for disk-based file items
        final DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(2_000_000);

        // Configure a repository (to ensure a secure temp location is used)
        final ServletContext servletContext = config.getServletContext();

        final File repository = (File) servletContext.getAttribute(tmp);

        factory.setRepository(repository);

        // Create a new file upload handler
        return new ServletFileUpload(factory);
    }
}
