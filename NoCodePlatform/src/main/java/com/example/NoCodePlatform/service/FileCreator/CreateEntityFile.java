package com.example.NoCodePlatform.service.FileCreator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.model.AttributesDetails;
import com.example.NoCodePlatform.model.RelationDetails;
import com.example.NoCodePlatform.service.generator.EntityGenerator;

public class CreateEntityFile {
            private static final String BASE_PATH = "C:/Users/Harish/OneDrive/Desktop/self/spring/baseProjectTest/src/main/java/com/example/baseProject";

    public static void create(String entityname, List<AttributesDetails> attributes, List<RelationDetails> relation){

        String entityCode = EntityGenerator.generate(entityname, attributes, relation);
        String targetDirectory = BASE_PATH+"/entity";
        String fileName = StringHelper.fileNameCompatible(entityname)+".java";
        // save the file in the base project

        try {
            //creating path object
            Path directoryPath = Paths.get(targetDirectory);
            //creating path directory if not their
            Files.createDirectories(directoryPath);

            //creating file
            Path filePath = directoryPath.resolve(fileName);
            //writing in file
            Files.writeString(filePath, entityCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }
}
