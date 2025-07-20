package com.example.NoCodePlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NoCodePlatform.model.EntityDetails;
import com.example.NoCodePlatform.service.CrudService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/crud")
public class CrudController {


    @Autowired
    private CrudService crudService;

    @PostMapping("/createcrud")
    public ResponseEntity<?> createCrud(@RequestBody List<EntityDetails> entityDetails) {
        System.out.println("create crud called...");
        
        for (EntityDetails entityDetails2 : entityDetails) {
            System.out.println(entityDetails2);
        }
        crudService.createCrud(entityDetails);

        return new ResponseEntity<>("crud generated successfully",HttpStatus.OK);
    }
}
