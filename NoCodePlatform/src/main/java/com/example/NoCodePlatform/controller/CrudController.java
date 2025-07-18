package com.example.NoCodePlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.NoCodePlatform.model.EntityDetails;
import com.example.NoCodePlatform.service.CrudService;

@RestController("/api/crud")
public class CrudController {

    @Autowired
    private CrudService crudService;
    
    public ResponseEntity<?> createCrud(@RequestBody List<EntityDetails> entityDetails){
        crudService.createCrud(entityDetails);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
