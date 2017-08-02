package ru.pravvich.dao;

/**
 * Contain all hql requests.
 */
public enum HQuery {
    GET_USER_BY_LOG_PASS("select u from User u where u.login = :login and u.password = :password"),
    SELECT_BY_MARK_AND_MODEL("select p from Propose p where p.mark =:mark and p.model =:model"),
    GET_MODELS_BY_MARKS("select DISTINCT p.model from Propose p where p.mark =:mark"),
    GET_ALL_PROPOSES("select p from Propose p join fetch p.auhtor where p.id > 0"),
    GET_ALL_MARKS("select DISTINCT p.mark from Propose p");


    String val;

    HQuery(final String val) {
        this.val = val;
    }
}
