package ru.pravvich.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

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
     * Propose id.
     */
    private User auhtor;
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
    private byte[] image;
    /**
     * For representation in jsp. Not using in ORM descriptors.
     */
    private String base64ImageFile;

    /**
     * Byte representation image.
     */
    public Propose() {
    }

    /**
     * Default constructor.
     *
     * @param author      of propose.
     * @param mark        of propose.
     * @param model       of propose.
     * @param description of propose.
     * @param image       of propose.
     */
    public Propose(final User author,
                   final String mark,
                   final String model,
                   final String description,
                   final byte[] image) {

        this.auhtor = author;
        this.description = description;
        this.mark = mark;
        this.model = model;
        this.sold = false;
        this.image = image;
    }

    public String getBase64ImageFile() {

        if (base64ImageFile != null) return base64ImageFile;

        if (image == null) return "";

        try {

            return base64ImageFile = new String(encodeBase64(image), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuhtor() {
        return auhtor;
    }

    public void setAuhtor(User auhtor) {
        this.auhtor = auhtor;
    }

    public boolean isSold() {
        return sold;
    }

    public boolean getSold() {
        return sold;
    }

    public void setSold(boolean sold) {
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
                ", auhtor=" + auhtor +
                ", sold=" + sold +
                ", description='" + description + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", image=" + "byte[" + image + "]" +
                '}';
    }
}
