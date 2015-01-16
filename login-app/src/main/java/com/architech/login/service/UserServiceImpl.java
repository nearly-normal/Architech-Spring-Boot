package com.architech.login.service;

import com.architech.login.dataaccess.UserRepository;
import com.architech.login.domain.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by satish on 15-01-15.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);

    }

    @Override
    public List<User> findAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public boolean isUserNameUnique(User user) {
        return (userRepository.findByUserName(user.getUserName()).size() == 0 ? Boolean.TRUE : Boolean.FALSE);
    }





}
