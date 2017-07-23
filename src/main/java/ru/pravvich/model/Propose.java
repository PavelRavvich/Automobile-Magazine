package ru.pravvich.model;

import java.io.Serializable;

/**
 * Author : Pavel Ravvich.
 * Created : 23.07.17.
 * <p>
 * Propose class representation propose of auto for sale.
 */
public class Propose implements Serializable {
    /**
     * Propose id.
     */
    private int id;
    /**
     * User id.
     */
    private int idAuhtor;
    /**
     * Sale status. False(default) - not sold. True - sold.
     */
    private boolean sold;
    /**
     * Description of propose.
     */
    private String description;
    /**
     * Mark of auto.
     */
    private String mark;
    /**
     * Model of auto.
     */
    private String model;
    /**
     * Constructor for Hibernate.
     */
    public Propose() {
    }

    /**
     * Default constructor.
     *
     * @param id of propose.
     * @param idAuhtor of user.
     * @param description of propose.
     * @param mark of propose.
     * @param model of propose.
     */
    public Propose(final int id,
                   final int idAuhtor,
                   final String description,
                   final String mark,
                   final String model) {
        this.id = id;
        this.idAuhtor = idAuhtor;
        this.description = description;
        this.mark = mark;
        this.model = model;
        this.sold = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAuhtor() {
        return idAuhtor;
    }

    public void setIdAuhtor(int idAuhtor) {
        this.idAuhtor = idAuhtor;
    }

    public boolean isSaleStatus() {
        return sold;
    }

    public void setSaleStatus(boolean sold) {
        this.sold = sold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Propose{" +
                "id=" + id +
                ", idAuhtor=" + idAuhtor +
                ", sold=" + sold +
                ", description='" + description + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
