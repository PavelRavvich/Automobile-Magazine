package ru.pravvich.model;

import java.io.Serializable;

/**
 * Author : Pavel Ravvich.
 * Created : 23.07.17.
 * <p>
 * User representation user.
 */
public class User implements Serializable {
    /**
     * User id.
     */
    private int id;
    /**
     * User login.
     */
    private String login;
    /**
     * User password.
     */
    private String password;
    /**
     * Constructor for Hibernate.
     */
    public User() {
    }

    /**
     * Default constructor.
     *
     * @param id of user.
     * @param login of user.
     * @param password of user.
     */
    public User(final int id,
                final String login,
                final String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
