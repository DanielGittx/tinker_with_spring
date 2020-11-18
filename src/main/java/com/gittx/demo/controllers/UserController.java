package com.gittx.demo.controllers;

import com.gittx.demo.entities.User;
import com.gittx.demo.exception_handlers.CustomException;
import com.gittx.demo.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static Logger logger = LogManager.getLogger();
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/fetch/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        logger.info("This is a GET Request to fetch all Users ");
        return userRepository.findAll();
    }

    @PostMapping(value = "/create/user")
    @Transactional(rollbackFor = Exception.class)   //Roll back incase an Exception occurs
    public @ResponseBody
    User createUser(@RequestBody User user) {
        logger.info("This is a POST Request to CREATE an User ");
        logger.info("User Object received (to be created) {}", user);

        //create
        return userRepository.save(user);
    }

    @GetMapping(value = "/fetch/user/{name}")
    public @ResponseBody
    User getUserByName(@PathVariable("name") String name) {
        logger.info("This is a GET Request to fetch User by name ");
        return userRepository.findByName(name).orElseThrow(()->new CustomException("User Not Found", 5003));

    }



}
