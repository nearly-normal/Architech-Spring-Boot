package com.architech.login.controller;

import com.architech.login.domain.User;
import com.architech.login.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by satish on 15-01-14.
 * A simple controller class which manages the url redirects and forwards
 * to the views.
 *
 * Also manages persisting the model via the service interface.
 *
 */
@Controller
@RequestMapping("/login")
public class UserController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;
    /*
     * This method will list all existing users.
     * Answers for any url starting at root and list
     */
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        //return new ModelAndView("allusers");
        return "allusers";

    }


    /**
     *
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"new"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "UserRegistration";
    }

    /**
     * create User from the model object.
     *
     * Forwards to all users after creation
     *
     * @param user
     * @param result
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"addUser"}, method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult result, ModelMap modelMap) {

        logger.debug("In Method to create user ...with User Object " + user);
        if (isUserNameUnique(user) == false) {
            result.rejectValue("userName","errors.username", "A user with this user name exists already");
        }

        if (result.hasErrors()) {
            logger.info("User Object has validation errors , " + result.getAllErrors());
            return "UserRegistration";
        }
        userService.createUser(user);
        logger.debug("Creatiing User Success");
        modelMap.addAttribute("success", "User " + user.getUserName()
                + " registered successfully");

        List<User> allUsers = userService.findAllUsers();
        modelMap.addAttribute("users", allUsers);
        return "allusers";
    }

    private boolean isUserNameUnique(User user) {
        return userService.isUserNameUnique(user);
    }


}
