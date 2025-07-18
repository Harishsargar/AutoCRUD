package com.example.NoCodePlatform.service.FileCreator;

import com.example.NoCodePlatform.service.generator.ControllerGenerator;

public class CreateControllerFile {
    public static void create(String entityName){

        String controllerCode = ControllerGenerator.generate(entityName);
        // save the file in the base project
    }
}
