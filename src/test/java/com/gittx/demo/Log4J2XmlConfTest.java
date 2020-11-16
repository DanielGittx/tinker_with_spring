package com.gittx.demo;

import com.gittx.demo.controllers.AccountController;
import org.junit.Test;
import static org.junit.Assert.*;
public class Log4J2XmlConfTest {
    @Test
    public void testPerformSomeTask() throws Exception {
        AccountController testLogger = new AccountController();
        testLogger.sayHi();
    }
}