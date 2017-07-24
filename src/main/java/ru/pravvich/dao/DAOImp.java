package ru.pravvich.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.pravvich.model.Propose;
import ru.pravvich.model.User;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Author : Pavel Ravvich.
 * Created : 23.07.17.
 * <p>
 * DAOImp
 */
public class DAOImp implements DAO {
    /**
     * Connection factory to database.
     */
    private final AtomicReference<SessionFactory> factory;

    /**
     * Default constructor.
     *
     * @param factory of connection to database.
     */
    public DAOImp(final AtomicReference<SessionFactory> factory) {
        this.factory = factory;
    }

    @Override
    public User getUser(final String login, final String password) {

        User result;

        final String hql = String.format("%s%s", "select u from User u ",
                "where u.login = :login and u.password = :password");

        try (final Session session = factory.get().openSession()) {

            result = session.createQuery(hql, User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        }

        return result;
    }

    @Override
    public Propose getProposeId(final int id) {

        Propose result;

        try (final Session session = factory.get().openSession()) {

            session.beginTransaction();

            result = session.get(Propose.class, id);
        }

        return result;
    }
}
