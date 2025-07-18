package com.example.NoCodePlatform.service.generator;

import com.example.NoCodePlatform.helper.StringHelper;

public class RepositoryGenerator {

    public static String generate(String entityName) {
        StringBuilder repository = new StringBuilder();
        String name = StringHelper.fileNameCompatible(entityName); // format HarishSargar
        String camelName = StringHelper.camelCase(entityName); // format harishSargar

        repository.append("package com.example.baseProject.repository;\r\n");
        repository.append("import org.springframework.data.jpa.repository.JpaRepository;\r\n\n");
        repository.append("import org.springframework.stereotype.Repository;\r\n");
        repository.append("import com.example.baseProject.entity."+name+";\r\n\n");

        repository.append("@Repository\r\n");
        repository.append("public interface "+name+"Repo extends JpaRepository<"+name+", String> {\r\n\n");

        repository.append("}\r\n");

        return repository.toString();

    }
}
