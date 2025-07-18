package com.example.NoCodePlatform.service.generator;

import java.util.List;

import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.model.AttributesDetails;

public class ServiceGenerator {

    public static String generate(String entityName, List<AttributesDetails> attributesDetails) {
        StringBuilder service = new StringBuilder();
        String name = StringHelper.fileNameCompatible(entityName); // format HarishSargar
        String camelName = StringHelper.camelCase(entityName); // format harishSargar

        service.append("   \r\n");

        service.append("package com.example.baseProject.service;\r\n\n");

        service.append("import java.util.UUID;\r\n\n");

        service.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        service.append("import org.springframework.stereotype.Service;\r\n\n");

        service.append("import com.example.baseProject.entity." + name + ";\r\n");
        service.append("import com.example.baseProject.repository." + name + "Repo;\r\n\n");

        service.append("@Service\r\n");
        service.append("public class " + name + "Service {\r\n\n");

        service.append("    @Autowired\r\n");
        service.append("    private " + name + "Repo " + camelName + "Repo;\r\n\n");

        service.append("    // create " + camelName + "\r\n");
        service.append("    public " + name + " create" + name + "(" + name + " " + camelName + "){\r\n");
        service.append("        " + camelName + ".set" + name + "Id(UUID.randomUUID().toString());\r\n");
        service.append("        return " + camelName + "Repo.save(" + camelName + ");\r\n");
        service.append("    }\r\n\n");

        service.append("    // get " + camelName + "\r\n");
        service.append("    public " + name + " get" + name + "byId(String id){\r\n");
        service.append("        return " + camelName + "Repo.findById(id).orElse(null);\r\n");
        service.append("    }\r\n\n");

        service.append("    // update " + camelName + "\r\n");
        service.append("    public " + name + " update" + name + "(" + name + " new" + name + "){\r\n");
        service.append("        " + name + " " + camelName + "  = new " + name + "();\r\n");

        for (AttributesDetails attributes : attributesDetails) {
            String values = StringHelper.fileNameCompatible(attributes.getName());
            service.append("        " + camelName + ".set" + values + "(new" + name + ".get" + values + "());\r\n");
        }
        // service.append(" "+camelName+".setName(new"+name+".getName());\r\n");
        // service.append(" "+camelName+".setLastName(new"+name+".getLastName());\r\n");
        // service.append(" "+camelName+".setAge(new"+name+".getAge());\r\n");
        service.append("        " + camelName + ".set" + name + "Id(new" + name + ".get" + name + "Id());\r\n");
        service.append("        return " + camelName + "Repo.save(" + camelName + ");\r\n");
        service.append("    }\r\n\n");

        service.append("    // delete " + camelName + "\r\n");
        service.append("    public void delete" + name + "ById(String id){\r\n");
        service.append("        " + camelName + "Repo.deleteById(id);\r\n");
        service.append("    }\r\n\n");
        service.append("}\r\n");

        return service.toString();
    }
}
