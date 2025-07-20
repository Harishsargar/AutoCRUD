package com.example.NoCodePlatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.NoCodePlatform.model.AttributesDetails;
import com.example.NoCodePlatform.model.EntityDetails;
import com.example.NoCodePlatform.model.RelationDetails;
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
            System.out.println("EntityName: "+entityname);
            List<AttributesDetails> attributesDetails = entity.getAttribute();
            List<RelationDetails> relation = entity.getRelation();

            // create controller
            // System.out.println("Creating Controller File: "+ entityname);
            // CreateControllerFile.create(entityname);

            // create respository
            // System.out.println("Creating Respository File: "+ entityname);
            // CreateRepositoryFile.create(entityname);

            // create service layer          
            // System.out.println("Creating Service File: "+ entityname);
            // CreateServiceFile.create(entityname, attributesDetails);

            // create entity
            System.out.println("Creating Entity File: "+ entityname);
            CreateEntityFile.create(entityname, attributesDetails, relation);


        }
    }

}
