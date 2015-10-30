package com.fjcasasaltas.sqlite.model;

import java.io.Serializable;

/**
 * TODO Description.
 *
 * @author fjcasasaltas
 * @since 1.0
 */
public class User implements Serializable {

    private Integer id;
    private String username;
    private Short pin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Short getPin() {
        return pin;
    }

    public void setPin(Short pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
