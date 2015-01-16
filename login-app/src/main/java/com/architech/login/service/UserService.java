package com.architech.login.service;

import com.architech.login.domain.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * A simple service interface exposing the service layer methods
 * <p/>
 * Created by satish on 15-01-15.
 */

public interface UserService {

    /**
     * creates user
     * @param user
     */
    public User createUser(User user);

    /**
     * Return all users in the system
     * @return
     */
    List<User> findAllUsers();

    /**
     * Check if the user name is unique
     * @param user
     * @return
     */
    boolean isUserNameUnique(User user);
}
