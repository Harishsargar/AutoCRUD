package com.example.NoCodePlatform.service.generator;

import java.util.List;

import org.springframework.scheduling.config.Task;

import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.model.RelationDetails;

public class RepositoryGenerator {

    public static String generate(String entityName, List<RelationDetails> relationDetails) {
        StringBuilder repository = new StringBuilder();
        String name = StringHelper.fileNameCompatible(entityName); // format HarishSargar
        // String camelName = StringHelper.camelCase(entityName); // format harishSargar
        System.out.println(" writing code: "+entityName);

        repository.append("package com.example.baseProject.repository;\r\n");
        repository.append("import org.springframework.data.jpa.repository.JpaRepository;\r\n\n");
        repository.append("import org.springframework.stereotype.Repository;\r\n");
        repository.append("import com.example.baseProject.entity."+name+";\r\n\n");
        

        if(relationDetails!=null){
            for (RelationDetails relation : relationDetails) {
                if(relation.getRelationType().equalsIgnoreCase("manytoone")){
                    repository.append("import java.util.List;\r\n");
                    repository.append("import com.example.baseProject.entity."+StringHelper.fileNameCompatible(relation.getTargetEntity())+";\r\n");
                }
            }
        }
        

        repository.append("@Repository\r\n");
        repository.append("public interface "+name+"Repo extends JpaRepository<"+name+", String> {\r\n\n");

        if(relationDetails!=null){
            for (RelationDetails relation : relationDetails) {
                
                String camelTarget = StringHelper.camelCase(relation.getTargetEntity());  // harishSargar
                String target = StringHelper.fileNameCompatible(relation.getTargetEntity());  // HarishSargar

                if(relation.getRelationType().equalsIgnoreCase("manytoone")){

                    repository.append("List<"+name+"> findBy"+target+"("+target+" "+camelTarget+");\r\n");
                    repository.append("void deleteBy"+target+""+target+"Id(String "+camelTarget+"Id);\r\n\n");
                    // List<Task> findByUser(User user);   
                    // void deleteByUserUserId(String userId);
                }
            }
        }

        repository.append("}\r\n");

        return repository.toString();

    }
}
