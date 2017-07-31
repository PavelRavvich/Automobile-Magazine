package ru.pravvich.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.pravvich.model.Propose;
import ru.pravvich.model.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Author : Pavel Ravvich.
 * Created : 23.07.17.
 * <p>
 * DAOImpl
 */
public class DAOImpl implements DAO {
    /**
     * Connection factory to database.
     */
    private final AtomicReference<SessionFactory> factory;

    /**
     * Default constructor.
     *
     * @param factory of connection to database.
     */
    public DAOImpl(final AtomicReference<SessionFactory> factory) {
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
    public Propose getProposeById(final int id) {

        Propose result;

        try (final Session session = factory.get().openSession()) {

            session.beginTransaction();

            result = session.get(Propose.class, id);

            Hibernate.initialize(result.getAuhtor());
        }

        return result;
    }

    @Override
    public List<Propose> getAllProposes() {

        List<Propose> result;

        final String query =
                "select p from Propose p join fetch p.auhtor where p.id > 0";

        try (final Session session = factory.get().openSession()) {

            final Query<Propose> id = session.createQuery(query, Propose.class);

            final Transaction transaction = session.beginTransaction();

            result = id.getResultList();
            transaction.commit();
        }

        return result;
    }

    @Override
    public void addPropose(final Propose propose) {
        try (final Session session = factory.get().openSession()) {

            session.beginTransaction();

            session.save(propose);

            session.getTransaction().commit();
        }
    }

    @Override
    public List<String> getMarks() {

        List<String> result;

        final String hq = "select DISTINCT p.mark from Propose p";

        try (final Session session = factory.get().openSession()) {

            result = session.createQuery(hq, String.class).getResultList();

        }

        return result;
    }

    @Override
    public List<String> getModelsByMark(final String mark) {

        List<String> result;

        final String hq =
                "select DISTINCT p.model from Propose p where p.mark =:mark";

        try (final Session session = factory.get().openSession()) {

            result = session.createQuery(hq, String.class)
                    .setParameter("mark", mark)
                    .getResultList();

        }

        return result;
    }
}
