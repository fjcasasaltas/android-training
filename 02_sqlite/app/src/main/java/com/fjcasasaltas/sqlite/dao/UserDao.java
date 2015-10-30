package com.fjcasasaltas.sqlite.dao;

import com.fjcasasaltas.sqlite.model.User;

import java.util.Collection;
import java.util.List;

/**
 * TODO Description.
 *
 * @author fjcasasaltas
 * @since 1.0
 */
public interface UserDao extends Dao {

    /**
     * TODO Description.
     *
     * @return
     */
    List<User> getAll();

}
