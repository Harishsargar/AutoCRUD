package com.example.NoCodePlatform.service.FileCreator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.service.generator.ControllerGenerator;

public class CreateControllerFile {
        private static final String BASE_PATH = "C:/Users/Harish/OneDrive/Desktop/self/spring/baseProjectTest/src/main/java/com/example/baseProject";


    public static void create(String entityName){

        String controllerCode = ControllerGenerator.generate(entityName);
        // save the file in the base project
        String targetDirectory = BASE_PATH + "/controller";
        String filename = StringHelper.fileNameCompatible(entityName)+"Controller.java";

        try {
            // creating directory
            Path directoryPath = Paths.get(targetDirectory);
            Files.createDirectory(directoryPath);

            //creating file
            Path filePath = directoryPath.resolve(filename);
            //writing code
            Files.writeString(filePath, controllerCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
