package com.example.NoCodePlatform.service.FileCreator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.model.RelationDetails;
import com.example.NoCodePlatform.service.generator.ControllerGenerator;

public class CreateControllerFile {
        private static final String BASE_PATH = "C:/Users/Harish/OneDrive/Desktop/self/spring/baseProjectTest/src/main/java/com/example/baseProject";


    public static void create(String entityName, List<RelationDetails> relationDetails ){
        System.out.println("CreateControllerFile called..");
        String controllerCode = ControllerGenerator.generate(entityName, relationDetails);
          System.out.println("code generated.." + controllerCode);
        // save the file in the base project
        String targetDirectory = BASE_PATH + "/controller";
         System.out.println("target directoery" + targetDirectory);
        String filename = StringHelper.fileNameCompatible(entityName)+"Controller.java";
        System.out.println("filename: "+ filename);
        try {
            // creating directory
            Path directoryPath = Paths.get(targetDirectory);
            Files.createDirectories(directoryPath);

            //creating file
            Path filePath = directoryPath.resolve(filename);
            //writing code
            Files.writeString(filePath, controllerCode);
            System.out.println("file updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
