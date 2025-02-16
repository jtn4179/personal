package com.mindex.challenge.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService 
{
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
    
    private String compensationFilePath = "compensation_database.json";

    private ObjectMapper objectMapper = new ObjectMapper();

    // create employee controller to allow access for the implementation
    @Autowired
    private CompensationRepository cr;

    @Override
    public Compensation create(Compensation compensation) 
    {
        LOG.debug("Creating Compensation [{}]", compensation);

        // put it in repo instance
        cr.insert(compensation);

        // add it to file for persistence
        List<Compensation> comps = readAll();
        comps.add(compensation);
        saveAll(comps);
        
        return compensation;
    }

    @Override
    public Compensation read(String employeeId) 
    {
        // dont need persistence stuff here since Bootstrap will load data to repo instance
        LOG.debug("Retrieving compensation for employee [{}]", employeeId);

        try 
        {
            Compensation comp = cr.findCompensationByEmployeeId(employeeId);

            if (comp == null) 
            {
                throw new RuntimeException("Invalid employeeId: " + employeeId);
            }

            return comp;
        }
        catch (RuntimeException notFound)
        {
            LOG.debug(notFound.getMessage());
        }

        return null;
    }

    @Override
    public List<Compensation> readAll() 
    {
        try 
        {
            Resource resource = new ClassPathResource(compensationFilePath);
            File file = resource.getFile();
            if (file.exists()) 
            {
                return objectMapper.readValue(file, new TypeReference<List<Compensation>>() {});
            } 
            else 
            {
                return new ArrayList<>();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void saveAll(List<Compensation> compensations) 
    {
        try 
        {
            Resource resource = new ClassPathResource(compensationFilePath);
            File file = resource.getFile();
            if (!file.exists()) 
            {
                file.createNewFile();
            }
            objectMapper.writeValue(file, compensations);
        } 
        catch (IOException e) 
        {
            LOG.debug(e.getMessage());
        }
    }
    
}
