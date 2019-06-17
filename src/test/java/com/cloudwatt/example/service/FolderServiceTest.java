package com.cloudwatt.example.service;

import com.cloudwatt.example.dao.JenkinsDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@DataJpaTest
public class FolderServiceTest {

    @Autowired
    private JenkinsService folderService;

    @InjectMocks
    private JenkinsDao jenkinsDao;

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

    @Test
    public void test_extractLastLine() {
        String expected = "Details: {u'message': u'No more floating IPs in pool 2e6d3c13-ac01-4265-8732-b209087b8d26.', u'code': 404}";
        Assert.assertEquals(expected, folderService.extractLastLine("\n\ndummy dummy\n  " + expected + "     \n\n      "));
    }

    @Test
    public void test_generateReportTestDetailUrl() {
        // Adapt
        String className = "tempest.api.object_storage.test_account_services.AccountTest";
        String name = "test_list_containers_with_limit_and_end_marker[id-888a3f0e-7214-4806-8e50-5e0c9a69bb5e]";
        // Act
        String actual = folderService.generateReportTestDetailUri(className, name);
        // Assert
        String expected = "tempest.api.object_storage.test_account_services/AccountTest/test_list_containers_with_limit_and_end_marker_id_888a3f0e_7214_4806_8e50_5e0c9a69bb5e_";
        Assert.assertEquals(expected, actual);
    }
}
