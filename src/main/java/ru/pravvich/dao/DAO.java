package ru.pravvich.dao;


import ru.pravvich.model.Propose;
import ru.pravvich.model.User;

public interface DAO {
    /**
     * Get user by login & password.
     *
     * @param login of Propose.
     * @param password of Propose.
     * @return Propose with appropriate pair login & password.
     */
    User getUser(String login, String password);

    /**
     * Get propose by Propose id.
     *
     * @param id of Propose.
     * @return Propose with appropriate id.
     */
    Propose getProposeId(int id);
}
