package com.architech.login.service;


import com.architech.login.dataaccess.UserRepository;
import com.architech.login.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by satish on 15-01-15.
 */
public class UserServiceTest {

    static UserRepository userRepository;

    @Before
    public  void setup(){
        userRepository= mock(UserRepository.class);

    }

    @Test
    public void testCreateUser(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.userRepository=userRepository;
        User user = new User();
        userService.createUser(user);
        verify(userRepository).save(user);
    }

    @Test
    public void testUserNameUnique(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.userRepository=userRepository;
        List<User> users= new ArrayList<>();
        when(userRepository.findByUserName("Test")).thenReturn(users);

        User user = new User();
        user.setUserName("Test");
        assertTrue(userService.isUserNameUnique(user));
        verify(userRepository).findByUserName("Test");

    }

    @Test
    public void testUserNameNotUnique(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.userRepository=userRepository;
        List<User> users= new ArrayList<>();
        users.add(new User());
        when(userRepository.findByUserName("Test")).thenReturn(users);

        User user = new User();
        user.setUserName("Test");
        assertFalse(userService.isUserNameUnique(user));
        verify(userRepository).findByUserName("Test");

    }





}
