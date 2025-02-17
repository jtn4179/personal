package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationPost;
    private String compensationGet;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationPost = "http://localhost:" + port + "/compensation";
        compensationGet = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateReadCompensation() {
        Compensation testCompensation = new Compensation();
        testCompensation.setEffectiveDate(new Date());
        testCompensation.setSalary(80.00);
        testCompensation.setEmployeeId("c0c2293d-16bd-4603-8e08-638a9d18b22c");

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationPost, testCompensation, Compensation.class).getBody();
        assertNotNull(createdCompensation.getEmployeeId());
        assertCompensationEquivalence(testCompensation, createdCompensation);

        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationGet, Compensation.class, testCompensation.getEmployeeId()).getBody();
        assertNotNull(readCompensation);
        assertCompensationEquivalence(testCompensation, readCompensation);
    }

    private void assertCompensationEquivalence(Compensation actual, Compensation expected)
    {
        assertEquals(actual.getEmployeeId(), expected.getEmployeeId());
        assertEquals(actual.getSalary(), expected.getSalary(), 0.00);
        assertEquals(actual.getEffectiveDate(), expected.getEffectiveDate());
    }
}
