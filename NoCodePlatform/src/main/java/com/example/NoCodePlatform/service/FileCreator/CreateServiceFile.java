package com.example.NoCodePlatform.service.FileCreator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.model.AttributesDetails;
import com.example.NoCodePlatform.service.generator.ServiceGenerator;

public class CreateServiceFile {
            private static final String BASE_PATH = "C:/Users/Harish/OneDrive/Desktop/self/spring/baseProjectTest/src/main/java/com/example/baseProject";

    public static void create(String entityName, List<AttributesDetails> attributes){
        String serviceCode = ServiceGenerator.generate(entityName, attributes);
        String targetDirectory = BASE_PATH+"/service";
        String filename = StringHelper.fileNameCompatible(entityName)+"Service.java";
        // save the file in the base project

        try {
            // create file obj
            Path directory = Paths.get(targetDirectory);
            //create directory if not present 
            Files.createDirectory(directory);
            //create file
            Path filePath = directory.resolve(filename);
            //write in a file
            Files.writeString(filePath, serviceCode);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
