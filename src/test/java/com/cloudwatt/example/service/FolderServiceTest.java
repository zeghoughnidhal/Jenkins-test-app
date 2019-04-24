package com.cloudwatt.example.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest
public class FolderServiceTest {

    @Autowired
    private FolderService folderService;

    @Test
    public void testNull() {
        String result = folderService.extractEnvFrom(null);
        Assert.assertEquals("NO_ENV", result);
    }

    @Test
    public void testEmpty() {
        String result = folderService.extractEnvFrom("");
        Assert.assertEquals("NO_ENV", result);
    }

    @Test
    public void testNoEnv() {
        String result = folderService.extractEnvFrom("test");
        Assert.assertEquals("NO_ENV", result);
    }

    @Test
    public void testProd() {
        String result = folderService.extractEnvFrom("test-int4");
        Assert.assertEquals("int4", result);
    }

}
