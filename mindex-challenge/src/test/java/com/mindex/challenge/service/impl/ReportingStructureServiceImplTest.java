package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String rsUrl;

    @Autowired
    private ReportingStructureService rsService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        rsUrl = "http://localhost:" + port + "/reporting/{employeeId}";
    }

    @Test
    public void testWithReports()
    {
        // setup
        String testId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        int expectedReports = 4;

        // invoke API
        ReportingStructure results = restTemplate.getForEntity(rsUrl, ReportingStructure.class, testId).getBody();
        
        // assert
        assertEquals(expectedReports, results.getNumberOfReports());
    }

    @Test
    public void testWithoutReports()
    {

        // setup
        String testId = "c0c2293d-16bd-4603-8e08-638a9d18b22c";
        int expectedReports = 0;

        // invoke API
        ReportingStructure results = restTemplate.getForEntity(rsUrl, ReportingStructure.class, testId).getBody();
        
        // assert
        assertEquals(expectedReports, results.getNumberOfReports());

    }
    
}
