package ru.pravvich.servlet;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.pravvich.dao.DAOImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Author : Pavel Ravvich.
 * Created : 24.07.17.
 * <p>
 * ContextListener
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private final SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        final DAOImpl dao = new DAOImpl(new AtomicReference<>(factory));

        sce.getServletContext().setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        factory.close();
    }
}
