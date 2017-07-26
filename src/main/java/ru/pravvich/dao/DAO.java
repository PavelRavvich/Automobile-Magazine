package ru.pravvich.dao;


import ru.pravvich.model.Propose;
import ru.pravvich.model.User;

import java.util.List;

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
    Propose getProposeById(int id);

    /**
     * Get all Proposes which contain in database.
     *
     * @return all proposes.
     */
    List<Propose> getAllProposes();
}
