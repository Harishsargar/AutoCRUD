package com.example.NoCodePlatform.service.generator;

import java.util.List;



import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.model.AttributesDetails;
import com.example.NoCodePlatform.model.RelationDetails;

public class ServiceGenerator {

    public static String generate(String entityName, List<AttributesDetails> attributesDetails,
            List<RelationDetails> relationDetails) {
        StringBuilder service = new StringBuilder();
        String name = StringHelper.fileNameCompatible(entityName); // format HarishSargar
        String camelName = StringHelper.camelCase(entityName); // format harishSargar
        System.out.println(" writing code: " + entityName);

        service.append("package com.example.baseProject.service;\r\n\n");

        service.append("import java.util.UUID;\r\n\n");

        service.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        service.append("import org.springframework.stereotype.Service;\r\n\n");
        

        service.append("import com.example.baseProject.entity." + name + ";\r\n");
        service.append("import com.example.baseProject.repository." + name + "Repo;\r\n\n");

        if (relationDetails != null) {
            service.append("import org.springframework.transaction.annotation.Transactional;\r\n");
            service.append("import java.util.List;\r\n");
            for (RelationDetails relation : relationDetails) {
                if (relation.getRelationType().equalsIgnoreCase("manytoone")) {
                    String target = StringHelper.fileNameCompatible(relation.getTargetEntity());
                    service.append("import com.example.baseProject.repository."+target+"Repo;\n\n");
                    service.append("import com.example.baseProject.entity."+target+";\n\n");
                }
            }
        }



        service.append("@Service\r\n");
        service.append("public class " + name + "Service {\r\n\n");

        service.append("    @Autowired\r\n");
        service.append("    private " + name + "Repo " + camelName + "Repo;\r\n\n");

        if (relationDetails != null) {
            for (RelationDetails relation : relationDetails) {
                if (relation.getRelationType().equalsIgnoreCase("manytoone")) {
                    String target = relation.getTargetEntity();
                    service.append("    @Autowired\r\n");
                    service.append("    private " + StringHelper.fileNameCompatible(target) + "Repo "
                            + StringHelper.camelCase(target) + "Repo;\r\n\n");
                }
            }
        }

        // ============= create entity code==============================================================================

        service.append("    // create " + camelName + "\r\n");
        service.append("    public " + name + " create" + name + "(" + name + " " + camelName);
        // adding extra parameters from relationship
        if (relationDetails != null) {
            for (RelationDetails relation : relationDetails) {
                if (relation.getRelationType().equalsIgnoreCase("manytoone")) {
                    String target = relation.getTargetEntity();
                    service.append(", String " + StringHelper.camelCase(target) + "Id");
                }
            }
            service.append("){\r\n");

            // setting the related(target) entity the current entity
            for (RelationDetails relation : relationDetails) {
                if (relation.getRelationType().equalsIgnoreCase("manytoone")) {
                    String target = StringHelper.fileNameCompatible(relation.getTargetEntity()); // HarishSargar
                    String cametTarget = StringHelper.camelCase(relation.getTargetEntity()); // harishSargar
                    service.append(camelName + ".set" + target + "(" + cametTarget + "Repo.findById(" + cametTarget
                            + "Id).orElse(null));\r\n");
                }
            }
        } else {
            service.append("){\r\n");
        }
        service.append("        " + camelName + ".set" + name + "Id(UUID.randomUUID().toString());\r\n");
        service.append("        return " + camelName + "Repo.save(" + camelName + ");\r\n");
        service.append("    }\r\n\n");

        // ===========================================================================================
        // ============== get entity cobe============================================================

        service.append("    // get " + camelName + "\r\n");
        service.append("    public " + name + " get" + name + "ById(String id){\r\n");
        service.append("        return " + camelName + "Repo.findById(id).orElse(null);\r\n");
        service.append("    }\r\n\n");

        // ===========================================================================================
        // ================ update entity code=======================================================

        service.append("    // update " + camelName + "\r\n");
        service.append("    public " + name + " update" + name + "(" + name + " new" + name + "){\r\n");
        service.append("        " + name + " " + camelName + "  = new " + name + "();\r\n");

        for (AttributesDetails attributes : attributesDetails) {
            String values = StringHelper.fileNameCompatible(attributes.getName());
            service.append("        " + camelName + ".set" + values + "(new" + name + ".get" + values + "());\r\n");
        }
        service.append("        " + camelName + ".set" + name + "Id(new" + name + ".get" + name + "Id());\r\n");

        if (relationDetails != null) {

            // updating the related(target) entity the new entity
            for (RelationDetails relation : relationDetails) {
                if (relation.getRelationType().equalsIgnoreCase("manytoone")) {
                    String target = StringHelper.fileNameCompatible(relation.getTargetEntity()); // HarishSargar
                    // String cametTarget = StringHelper.camelCase(relation.getTargetEntity()); // harishSargar
                    service.append(camelName + ".set" + target + "(new" + name + ".get" + target + "());\r\n");

                    
                }
            }
        }
        service.append("        return " + camelName + "Repo.save(" + camelName + ");\r\n");
        service.append("    }\r\n\n");

        // ===========================================================================================
        // ===================== delete entity code==================================================

        service.append("    // delete " + camelName + "\r\n");
        service.append("    public void delete" + name + "ById(String id){\r\n");
        service.append("        " + camelName + "Repo.deleteById(id);\r\n");
        service.append("    }\r\n\n");

        // ===========================================================================================
        // ============ only if relation exisit then this code will be generated=====================


        if (relationDetails != null) {

            // updating the related(target) entity the new entity
            for (RelationDetails relation : relationDetails) {
                if (relation.getRelationType().equalsIgnoreCase("manytoone")) {
                    String target = StringHelper.fileNameCompatible(relation.getTargetEntity()); // HarishSargar
                    String camelTarget = StringHelper.camelCase(relation.getTargetEntity());  // harishSargar

                    service.append("        // get all "+camelName+" by "+camelTarget+"id\r\n");
                    service.append("    public List<"+name+"> getAll"+name+"by"+target+"Id(String id){\r\n");
                    service.append("        "+target+" "+camelTarget+" = "+camelTarget+"Repo.findById(id).orElse(null);\r\n");
                    service.append("        return "+camelName+"Repo.findBy"+target+"("+camelTarget+");\r\n");
                    service.append("    }\r\n");

                    service.append("    //delete all "+camelName+" by "+camelTarget+"id\r\n");
                    service.append("    @Transactional      // without this it was givng an error\r\n");
                    service.append("    public void deleteAll"+name+"ById(String "+camelTarget+"id){\r\n");
                    service.append("        "+camelName+"Repo.deleteBy"+target+""+target+"Id("+camelTarget+"id);\r\n");
                    service.append("    }\r\n");
                }
            }
        }

                service.append("}\r\n");







        // ===========================================================================================

        return service.toString();
    }
}
