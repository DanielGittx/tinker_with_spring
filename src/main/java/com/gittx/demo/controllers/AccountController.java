package com.gittx.demo.controllers;

import com.gittx.demo.data_objects.AccountsDto;
import com.gittx.demo.entities.User;
import com.gittx.demo.exception_handlers.CustomException;
import com.gittx.demo.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AccountController {

    private static Logger logger = LogManager.getLogger();
    private Map<Integer, AccountsDto> accountRepo = new HashMap<>();
    List<Integer> listOfIds = new ArrayList<>();

    //Read from database
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/hello")
    public @ResponseBody
    String sayHi() {
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
        return "Test";
    }

    @GetMapping(value = "/fetch/accounts")
    public @ResponseBody
    Map getAll() {
        logger.info("This is a GET Request to fetch all accounts ");
        return accountRepo;
    }

    @GetMapping(value = "/fetch/account/{id}")
    public @ResponseBody
    AccountsDto getOneAccount(@PathVariable("id") Integer id) {
        logger.info("This is a GET Request to fetch one accounts ");

        if (!accountRepo.containsKey(id)) {
            logger.warn("Key NOT FOUND :- ", id);
            throw new CustomException("Account Not Found", 5001);
        }

        return accountRepo.get(id);

    }

    @PostMapping(value = "/create/account")
    public @ResponseBody
    AccountsDto createAccount(@RequestBody AccountsDto account) {
        logger.info("This is a POST Request to CREATE an account ");
        logger.info("Account branch received:- " + account.getBranch());
        logger.info("Account Object received (to be created) {}", account);

        //create
        accountRepo.put(new Random().nextInt(1000), account);
        logger.info("Account Repo {} :- ", accountRepo);
        logger.info("\nSize of Map {} ", accountRepo.size());

        return account;
    }

    @PutMapping(value = "/update/account/{id}")
    public @ResponseBody
    AccountsDto updateAccount(@RequestBody AccountsDto account, @PathVariable("id") Integer id)
            throws CustomException {
        logger.info("This is a PUT Request to UPDATE an account ");

//        accountRepo.entrySet().stream()
//                .filter(p->p.getKey() == id)
//                .forEach(p->account.setId(id));  //test

        if (!accountRepo.containsKey(id)) {
            logger.warn("Key NOT FOUND :- ", id);
            throw new CustomException("Account Not Found", 5001);
        }

        logger.info("Key FOUND : All good!!!");
        accountRepo.put(id, account);
        return account;

    }

    @DeleteMapping(value = "/delete/account/{id}")
    public @ResponseBody
    Map deleteOneAccount(@PathVariable("id") Integer id) {
        logger.info("This is a GET Request to fetch one accounts ");

        if (!accountRepo.containsKey(id)) {
            logger.warn("Key NOT FOUND :- ", id);
            throw new CustomException("Account Not Found", 5001);
        }

        logger.info("Deleted account ID {}", id);
        accountRepo.remove(id);
        logger.info("New Repo :- \n", accountRepo);
        logger.info("\nSize of Map {} ", accountRepo.size());

        return accountRepo;

    }

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
