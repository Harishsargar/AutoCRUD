package com.example.NoCodePlatform.service.FileCreator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.service.generator.RepositoryGenerator;

public class CreateRepositoryFile {
            private static final String BASE_PATH = "C:/Users/Harish/OneDrive/Desktop/self/spring/baseProjectTest/src/main/java/com/example/baseProject";

    public static void create(String entityName){

        String repoCode = RepositoryGenerator.generate(entityName);
        String targetDirectory = BASE_PATH+"/repository";
        String fileName = StringHelper.fileNameCompatible(entityName)+"Repo.java";
        // save the file in the base project
        
        try {
            //create path object
            Path directory  = Paths.get(targetDirectory);
            // create directory if not present
            Files.createDirectories(directory);
            //create file
            Path filePath = directory.resolve(fileName);
            Files.writeString(filePath, repoCode);

            //write in file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
