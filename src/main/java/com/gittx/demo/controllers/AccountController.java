package com.gittx.demo.controllers;

import com.gittx.demo.data_objects.AccountsDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AccountController {

    private static Logger logger = LogManager.getLogger();

    @GetMapping(value = "/hello")
    public @ResponseBody String sayHi()
    {
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
        return "Test";
    }

    @GetMapping(value = "/accounts")
    public @ResponseBody List<AccountsDto> getAll()
    {
        logger.info("This is a GET Request to fetch all accounts ");
        AtomicLong id = new AtomicLong();

        List<AccountsDto> accountsList = new ArrayList<>();
        for (long x = 0; x < 5; x++) {
            accountsList.add(new AccountsDto("premium", x, "active", "yaya", new Date()));
        }
        return accountsList;
    }

    @GetMapping(value = "/accounts/{id}")
    public @ResponseBody AccountsDto getOneAccount(@PathVariable int id)
    {
        logger.info("This is a GET Request to fetch one accounts ");

        return new AccountsDto("premium",id,"active","yaya",new Date());
    }

    @PostMapping(value = "/account")
    public @ResponseBody AccountsDto createAccount(@RequestBody AccountsDto account)
    {
        logger.info("This is a POST Request to CREATE an account ");
        logger.info("Account branch received:- "+ account.getBranch());
        logger.info("Account Object received {}", account);

        return account;
    }


}
