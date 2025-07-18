package com.example.NoCodePlatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.NoCodePlatform.model.AttributesDetails;
import com.example.NoCodePlatform.model.EntityDetails;
import com.example.NoCodePlatform.service.FileCreator.CreateControllerFile;
import com.example.NoCodePlatform.service.FileCreator.CreateEntityFile;
import com.example.NoCodePlatform.service.FileCreator.CreateRepositoryFile;
import com.example.NoCodePlatform.service.FileCreator.CreateServiceFile;

@Service
public class CrudService {

    public void createCrud(List<EntityDetails> entityDetails) {
        System.out.println(" crud service called..");
        for (EntityDetails entity : entityDetails) {

            String entityname = entity.getEntityName();
            System.out.println(entityname+" == in the for loop");
            List<AttributesDetails> attributesDetails = entity.getAttribute();

            // create controller
            CreateControllerFile.create(entityname);

            // create respository
            CreateRepositoryFile.create(entityname);

            // create service layer          
            // CreateServiceFile.create(entityname, attributesDetails);

            // create entity
            // CreateEntityFile.create(entityname, attributesDetails);


        }
    }

}
