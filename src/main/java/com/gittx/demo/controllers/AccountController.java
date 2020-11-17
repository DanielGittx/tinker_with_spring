package com.gittx.demo.controllers;

import com.gittx.demo.data_objects.AccountsDto;
import com.gittx.demo.exception_handlers.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AccountController {

    private static Logger logger = LogManager.getLogger();

    private static Map<Long, AccountsDto> accountRepo = new HashMap<>();

    static {   //before we spin a database
        AccountsDto ac1 = new AccountsDto("premium", 1, "active", "yaya", new Date());
        accountRepo.put(ac1.getId(), ac1);
    }

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
    List<AccountsDto> getAll() {
        logger.info("This is a GET Request to fetch all accounts ");
        AtomicLong id = new AtomicLong();

        List<AccountsDto> accountsList = new ArrayList<>();
        for (long x = 0; x < 5; x++) {
            accountsList.add(new AccountsDto("premium", x, "active", "yaya", new Date()));
        }
        return accountsList;
    }

    @GetMapping(value = "/fetch/account/{id}")
    public @ResponseBody
    AccountsDto getOneAccount(@PathVariable("id") Long id) {
        logger.info("This is a GET Request to fetch one accounts ");

        return new AccountsDto("premium", id, "active", "yaya", new Date());

    }

    @PostMapping(value = "/create/account")
    public @ResponseBody
    AccountsDto createAccount(@RequestBody AccountsDto account) {
        logger.info("This is a POST Request to CREATE an account ");
        logger.info("Account branch received:- " + account.getBranch());
        logger.info("Account Object received {}", account);
        return account;
    }

    @PutMapping(value = "/update/account/{id}")
    public @ResponseBody
    AccountsDto updateAccount(@RequestBody AccountsDto account, @PathVariable("id") Long id, HttpServletRequest request) throws CustomException {
        logger.info("This is a PUT Request to UPDATE an account ");

//        accountRepo.entrySet().stream()
//                .filter(p->p.getKey() == id)
//                .forEach(p->account.setId(id));  //test

        if (!accountRepo.containsKey(id)) {
            logger.warn("Key NOT FOUND :- ", id);
            throw new CustomException("Account Not Found", 5001);
        }

        logger.warn("Key FOUND : All good!!!");
        account.setId(id);
        return account;

    }


}
