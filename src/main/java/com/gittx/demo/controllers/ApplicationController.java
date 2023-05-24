package com.gittx.demo.controllers;

import com.gittx.demo.data_objects.AccountsDto;
import com.gittx.demo.entities.Application;
import com.gittx.demo.entities.Kyc;
import com.gittx.demo.entities.Stat;
import com.gittx.demo.exception_handlers.CustomException;
import com.gittx.demo.repositories.ApplicationRepository;
import com.gittx.demo.services.FileStorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/v1")
public class ApplicationController {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    FileStorageService fileStorageService;

    @PostMapping(value = "/apply")
    public @ResponseBody
    Application createAccount(@RequestBody Application application) {
        application.setStatus(Stat.PENDING);
        application.setDate_created(new Date());
        applicationRepository.save(application);

        return application;
    }

    @PostMapping(value = "/kyc/upload")
    public @ResponseBody HashMap<String,String> uploadKyc (@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("Upload file ....");
        String fileLocation = fileStorageService.storeFile(file);
        System.out.println("File uploaded successfully ....");
        HashMap<String,String> map=new HashMap<String,String>();//Creating HashMap.
        map.put("location",fileLocation);


        return map;
    }


}
