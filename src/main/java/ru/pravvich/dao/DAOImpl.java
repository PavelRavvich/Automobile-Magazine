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

import static ru.pravvich.dao.HQuery.*;

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

    /**
     * Get user by login & password.
     */
    @Override
    public User getUser(final String login, final String password) {

        try (final Session session = factory.get().openSession()) {

            final List<User> select = session
                    .createQuery(GET_USER_BY_LOG_PASS.val, User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .list();

            return select.isEmpty() ? new User() : select.get(0);
        }
    }

    /**
     * Get propose by Propose id.
     */
    @Override
    public Propose getProposeById(final int id) {

        try (final Session session = factory.get().openSession()) {

            session.beginTransaction();

            final Propose result = session.get(Propose.class, id);

            Hibernate.initialize(result.getAuhtor());

            return result;
        }
    }

    /**
     * Get all Proposes which contain in database.
     *
     * @return all proposes.
     */
    @Override
    public List<Propose> getAllProposes() {

        try (final Session session = factory.get().openSession()) {

            final Query<Propose> id = session
                    .createQuery(GET_ALL_PROPOSES.val, Propose.class);

            final Transaction transaction = session.beginTransaction();

            final List<Propose> result = id.getResultList();

            transaction.commit();

            return result;
        }
    }

    /**
     * Addition new propose.
     */
    @Override
    public void addPropose(final Propose propose) {

        try (final Session session = factory.get().openSession()) {

            session.beginTransaction();

            session.save(propose);

            session.getTransaction().commit();
        }
    }

    /**
     * Get all unique marks set.
     */
    @Override
    public List<String> getMarks() {

        try (final Session session = factory.get().openSession()) {

            return session.createQuery(GET_ALL_MARKS.val, String.class)
                    .getResultList();
        }
    }

    /**
     * Get all models corresponded mark of Car.
     */
    @Override
    public List<String> getModelsByMark(final String mark) {

        try (final Session session = factory.get().openSession()) {

            return session.createQuery(GET_MODELS_BY_MARKS.val, String.class)
                    .setParameter("mark", mark)
                    .getResultList();
        }
    }

    /**
     * Select all propose with valid mark and model.
     */
    public List<Propose> selectBy(final String mark, final String model) {

        try (Session session = factory.get().openSession()) {

            final List<Propose> result = session
                    .createQuery(SELECT_BY_MARK_AND_MODEL.val, Propose.class)
                    .setParameter("mark", mark)
                    .setParameter("model", model)
                    .getResultList();

            result.stream()
                    .map(Propose::getAuhtor)
                    .forEach(Hibernate::initialize);

            return result;
        }
    }

    @Override
    public void update(final Propose propose) {

        try (Session session = factory.get().openSession()) {

            session.beginTransaction();

            session.update(propose);

            session.getTransaction().commit();
        }
    }
}
