package ru.pravvich.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;
import ru.pravvich.model.Propose;
import ru.pravvich.model.User;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DAOImplTest {

    private AtomicReference<SessionFactory> fac;
    private DAO dao;

    private final User user = new User("test", "test");
    private final Propose propose = new Propose(
            user, "mark", "model", "desc", new byte[]{});


    @Before
    public void before() {
        fac = new AtomicReference<>(
                new Configuration().configure().buildSessionFactory()
        );

        dao = new DAOImpl(fac);
    }

    @After
    public void after() {
        fac.get().close();
    }

    @Test
    public void whenAddNewUserAndGetUserByLoginPasswordThanUserExistInDB() {

        dao.addUser(new User("test", "test"));

        assertNotNull(dao.getUser("test", "test"));
    }

    @Test
    public void whenGetNotExistUserThenGetEmptyUser() {

        final User user = dao.getUser("test", "test");

        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertThat(user.getId(), is(0));
    }

    @Test
    public void whenGetProposeByIdWhichExistThenReturnPropose() {

        dao.addUser(user);
        dao.addPropose(propose);

        assertThat(dao.getProposeById(1).getId(), is(1));
    }

    @Test
    public void whenGetProposeByIdWichNotExistThenReturnEmptyPropose() {
        assertNull(dao.getProposeById(1));
    }

    @Test
    public void whenGetAllProposesWhichNotEmptyThenReturnFullList() {
        dao.addUser(user);
        dao.addPropose(propose);
        dao.addPropose(propose);

        assertThat(dao.getAllProposes().size(), is(2));
    }

    @Test
    public void whenGetAllProposesWithEmptyTableThenReturnEmptyList() {
        assertThat(dao.getAllProposes().size(), is(0));
    }

    @Test
    public void whenGetMarksThenGetListWithAllMarksSet() {
        dao.addUser(user);
        dao.addPropose(propose);
        assertThat(dao.getMarks().size(), is(1));
    }

    @Test
    public void whenGetModelByMarkThenReturnSetOfAllModelAssociatedWithMark() {
        dao.addUser(user);
        dao.addPropose(propose);
        assertThat(dao.getModelsByMark("mark").get(0), is("model"));
    }

    @Test
    public void whenUpdateProposeThenProposeSetNewState() {
        dao.addUser(user);
        dao.addPropose(propose);

        final Propose before = dao.getProposeById(1);

        propose.setSold(true);
        dao.update(propose);

        final Propose after = dao.getProposeById(1);

        assertFalse(before.getSold());
        assertTrue(after.getSold());
    }
}