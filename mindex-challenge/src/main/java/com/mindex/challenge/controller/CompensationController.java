package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService cs;

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation)
    {
        LOG.debug("Received CREATE request for compensation [{}]", compensation);

        return cs.create(compensation);
    }

    @GetMapping("/compensation/{employeeId}")
    public Compensation read(@PathVariable String employeeId)
    {
        LOG.debug("Received GET request for compensation with employeeId [{}]", employeeId);

        return cs.read(employeeId);
    }
    
}
