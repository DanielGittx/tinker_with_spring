package com.gittx.demo.controllers;

import com.gittx.demo.data_objects.AccountsDto;
import com.gittx.demo.exception_handlers.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private static Logger logger = LogManager.getLogger();
    private Map<Integer, AccountsDto> accountRepo = new HashMap<>();
    List<Integer> listOfIds = new ArrayList<>();

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

    @GetMapping(value = "/fetch")
    public @ResponseBody
    Map getAll() {
        logger.info("This is a GET Request to fetch all accounts ");
        return accountRepo;
    }

    @GetMapping(value = "/fetch/{id}")
    public @ResponseBody
    AccountsDto getOneAccount(@PathVariable("id") Integer id) {
        logger.info("This is a GET Request to fetch one accounts ");

        if (!accountRepo.containsKey(id)) {
            logger.warn("Key NOT FOUND :- ", id);
            throw new CustomException("Account Not Found", 5001);
        }

        return accountRepo.get(id);

    }

    @PostMapping(value = "/create")
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

    @PutMapping(value = "/update/{id}")
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

    @DeleteMapping(value = "/delete/{id}")
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

}
