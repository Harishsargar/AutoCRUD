package com.example.NoCodePlatform.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.NoCodePlatform.controller.CrudController;
import com.example.NoCodePlatform.model.AttributesDetails;
import com.example.NoCodePlatform.model.EntityDetails;
import com.example.NoCodePlatform.model.RelationDetails;
import com.example.NoCodePlatform.service.FileCreator.CreateControllerFile;
import com.example.NoCodePlatform.service.FileCreator.CreateEntityFile;
import com.example.NoCodePlatform.service.FileCreator.CreateRepositoryFile;
import com.example.NoCodePlatform.service.FileCreator.CreateServiceFile;

@Service
public class CrudService {

    private static final String BASE_PATH = "C:/Users/Harish/OneDrive/Desktop/self/spring/baseProjectTest";
    private static final Logger logger = LoggerFactory.getLogger(CrudController.class);

    public String createCrud(List<EntityDetails> entityDetails) throws IOException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // System.out.println(" crud service called..");
        logger.info(" crud service called..");
        for (EntityDetails entity : entityDetails) {

            String entityname = entity.getEntityName();
            System.out.println("EntityName: " + entityname);
            List<AttributesDetails> attributesDetails = entity.getAttribute();
            List<RelationDetails> relation = entity.getRelation();

            // ===========sequential_approch=============================================

            // // create controller
            // System.out.println("Creating Controller File: " + entityname);
            // CreateControllerFile.create(entityname, relation);

            // // create respository
            // System.out.println("Creating Respository File: " + entityname);
            // CreateRepositoryFile.create(entityname, relation);

            // // create service layer
            // System.out.println("Creating Service File: " + entityname);
            // CreateServiceFile.create(entityname, attributesDetails, relation);

            // // create entity
            // System.out.println("Creating Entity File: " + entityname);
            // CreateEntityFile.create(entityname, attributesDetails, relation);

            // =======multithrading_approch=================================================

            // create controller
            // Thread controller = new Thread(() -> {
            //     System.out.println("1. Creating Controller File: " + entityname);
            //     CreateControllerFile.create(entityname, relation);
            // });
            // controller.start();

            // // create service
            // Thread service = new Thread(() -> {
            //     System.out.println("2. Creating service File: " + entityname);
            //     CreateServiceFile.create(entityname, attributesDetails, relation);
            // });
            // service.start();

            // // create repository
            // Thread repository = new Thread(() -> {
            //     System.out.println("3. Creating repository File: " + entityname);
            //     CreateRepositoryFile.create(entityname, relation);
            // });
            // repository.start();

            // // create entity
            // Thread entityFile = new Thread(() -> {
            //     System.out.println("4. Creating entity File: " + entityname);
            //     CreateEntityFile.create(entityname, attributesDetails, relation);
            // });
            // entityFile.start();

            // // Wait for all threads to complete
            // controller.join();
            // service.join();
            // repository.join();
            // entityFile.join();


            //======exceutor_service_thread====================================================================================

            executorService.submit(()->{
                 System.out.println("1. Creating Controller File: " + entityname);
                CreateControllerFile.create(entityname, relation);
            });

            executorService.submit(()->{
                System.out.println("2. Creating Service File: "+entityname);
                CreateServiceFile.create(entityname, attributesDetails, relation);
            });

             executorService.submit(()->{
                System.out.println("3. Creating Repository File: "+entityname);
                CreateRepositoryFile.create(entityname, relation);
            });

             executorService.submit(()->{
                System.out.println("4. Creating Entity File: "+entityname);
                CreateEntityFile.create(entityname, attributesDetails, relation);
            });


        }

        // executorService.shutdown();
        // executorService.awaitTermination(1, TimeUnit.MINUTES);
        return zipFolder(BASE_PATH);
    }

    public String zipFolder(String sourceDirPath) throws IOException {
        System.out.println("creating zip...");
        String zipPath = sourceDirPath + ".zip";
        Path zipFilePath = Paths.get(zipPath);

        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(zipFilePath))) {
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
        System.out.println(zipPath);
        return zipPath; // 👈 This returns something like: C:\Users\Harish\...\baseProjectTest.zip
    }

}
