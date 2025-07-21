package com.example.NoCodePlatform.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.example.NoCodePlatform.model.EntityDetails;
import com.example.NoCodePlatform.service.CrudService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/crud")
public class CrudController {


    @Autowired
    private CrudService crudService;

    private static final Logger logger = LoggerFactory.getLogger(CrudController.class);

    @PostMapping("/createcrud")
    public ResponseEntity<?> createCrud(@RequestBody List<EntityDetails> entityDetails) throws IOException, InterruptedException {
        // System.out.println("create crud called...");
        logger.info("Create Crud Called...");
        
        for (EntityDetails entityDetails2 : entityDetails) {
            // System.out.println(entityDetails2);
            logger.info("Entity Detail: {}",entityDetails2);
        }
        String zipPath = crudService.createCrud(entityDetails);
        Path path = Paths.get(zipPath);
        UrlResource resource = new UrlResource(path.toUri());
        if(!resource.exists()){
            return new ResponseEntity<>("Project Not Found",HttpStatus.NOT_FOUND);
        }


        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString())
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .body(resource);

        // return new ResponseEntity<>(HttpStatus.OK);
    }
}
