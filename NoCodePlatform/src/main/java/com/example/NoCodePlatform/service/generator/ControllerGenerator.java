package com.example.NoCodePlatform.service.generator;

import java.util.List;
import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.model.RelationDetails;

public class ControllerGenerator {
    public static String generate(String entityName, List<RelationDetails> relationDetails) {
        String name = StringHelper.fileNameCompatible(entityName);
        String camelName = StringHelper.camelCase(entityName);
        StringBuilder controller = new StringBuilder();

        controller.append("package com.example.baseProject.controller;\r\n\n");

        controller.append("import java.util.List;\r\n\n");
        controller.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        controller.append("import org.springframework.http.HttpStatus;\r\n");
        controller.append("import org.springframework.http.ResponseEntity;\r\n");
        controller.append("import org.springframework.web.bind.annotation.DeleteMapping;\r\n");
        controller.append("import org.springframework.web.bind.annotation.GetMapping;\r\n");
        controller.append("import org.springframework.web.bind.annotation.PathVariable;\r\n");
        controller.append("import org.springframework.web.bind.annotation.PostMapping;\r\n");
        controller.append("import org.springframework.web.bind.annotation.PutMapping;\r\n");
        controller.append("import org.springframework.web.bind.annotation.RequestBody;\r\n");
        controller.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
        controller.append("import org.springframework.web.bind.annotation.RestController;\r\n\n");

        controller.append("import com.example.baseProject.entity." + name + ";\r\n");
        controller.append("import com.example.baseProject.service." + name + "Service;\r\n\n");

        controller.append("@RestController\r\n");
        controller.append("@RequestMapping(\"/api/" + camelName + "\")\r\n");
        controller.append("public class " + name + "Controller {\r\n\n");

        controller.append("    @Autowired\r\n");
        controller.append("    private " + name + "Service " + camelName + "Service;\r\n\n");

        //===============createEntity=======================================================================================

        controller.append("    // create " + camelName + "\r\n");
        controller.append("    @PostMapping(\"/create" + camelName);
        if (relationDetails != null) {
            for (RelationDetails relation : relationDetails) {
                if (relation.getRelationType().equalsIgnoreCase("manytoone")) {
                    String target = relation.getTargetEntity();
                    controller.append("/{" + StringHelper.camelCase(target) + "Id}");
                }
            }
        }
        controller.append("\")\r\n");


        controller.append("    public ResponseEntity<" + name + "> create" + name + "(@RequestBody " + name + " "+ camelName);
        if (relationDetails != null) {
            for (RelationDetails relation : relationDetails) {
                if (relation.getRelationType().equalsIgnoreCase("manytoone")) {
                    String target = relation.getTargetEntity();
                    controller.append(", @PathVariable String "+StringHelper.camelCase(target)+"Id");
                }
            }
        }
        controller.append("){\r\n");
        controller.append("        " + name + " saved" + name + " = " + camelName + "Service.create" + name + "("+ camelName);
        if (relationDetails != null) {
            for (RelationDetails relation : relationDetails) {
                if (relation.getRelationType().equalsIgnoreCase("manytoone")) {
                    String target = relation.getTargetEntity();
                    controller.append(", "+StringHelper.camelCase(target)+"Id");
                }
            }
        }
        controller.append(");\r\n");
        controller.append("        return new ResponseEntity<>(saved" + name + ", HttpStatus.CREATED);\r\n");
        controller.append("    }\r\n\n");

        // ===================================================================================================================
        //===============getEntity============================================================================================

        controller.append("    // get " + camelName + "\r\n");
        controller.append("    @GetMapping(\"/get" + camelName + "/{id}\")\r\n");
        controller.append("    public ResponseEntity<" + name + "> get" + name + "(@PathVariable String id){\r\n");
        controller.append("        " + name + " " + camelName + " = " + camelName + "Service.get" + name + "ById(id);\r\n");
        controller.append("        return new ResponseEntity<>(" + camelName + ", HttpStatus.OK);\r\n");
        controller.append("    }\r\n\n");

        //=====================================================================================================================
        //===============updateEntity==========================================================================================
        controller.append("    // update " + camelName + "\r\n");
        controller.append("    @PutMapping(\"/update" + camelName + "\")\r\n");
        controller.append("    public ResponseEntity<" + name + "> update" + name + "(@RequestBody " + name + " "+ camelName + "){\r\n");
        controller.append("        " + name + " updated" + name + " = " + camelName + "Service.update" + name + "("+ camelName + ");\r\n");
        controller.append("        return new ResponseEntity<>(updated" + name + ", HttpStatus.OK);\r\n");
        controller.append("    }\r\n\n");

        //======================================================================================================================
        //============deleteEntity==============================================================================================

        controller.append("    // delete " + camelName + "\r\n");
        controller.append("    @DeleteMapping(\"/delete" + camelName + "/{id}\")\r\n");
        controller.append("    public ResponseEntity<Void> delete" + name + "(@PathVariable String id){\r\n");
        controller.append("        " + camelName + "Service.delete" + name + "ById(id);\r\n");
        controller.append("        return new ResponseEntity<>(HttpStatus.NO_CONTENT);\r\n");
        controller.append("    }\r\n");

        //=======================================================================================================================
        //================get_all_entity_by_relatedEntity_id=====================================================================

        if (relationDetails != null) {
            for (RelationDetails relation : relationDetails) {
                if (relation.getRelationType().equalsIgnoreCase("manytoone")) {
                    String target = StringHelper.fileNameCompatible(relation.getTargetEntity());
                    String camelTarget = StringHelper.camelCase(relation.getTargetEntity());
                    controller.append("         // get all "+camelName+" by "+camelTarget+" id\r\n");
                    controller.append("    @GetMapping(\"/getall"+camelName+"by"+camelTarget+"/{id}\")\r\n");
                    controller.append("    public ResponseEntity<List<"+name+">> getAll"+name+"By"+target+"(@PathVariable String id){\r\n");
                    controller.append("        List<"+name+"> all"+camelName+" = "+camelName+"Service.getAll"+name+"by"+target+"Id(id);\r\n");
                    controller.append("        return new ResponseEntity<>(all"+camelName+", HttpStatus.OK);\r\n");
                    controller.append("    }\r\n");

                    controller.append("    //delete all "+camelName+" by "+camelTarget+"id\r\n");
                    controller.append("    @DeleteMapping(\"/deleteall"+camelName+"by"+camelTarget+"/{id}\")\r\n");
                    controller.append("    public ResponseEntity<Void> deleteAll"+name+"By"+target+"(@PathVariable String id){\r\n");
                    controller.append("        System.out.println(id);\r\n");
                    controller.append("        "+camelName+"Service.deleteAll"+name+"ById(id);\r\n");
                    controller.append("        return new ResponseEntity<>(HttpStatus.NO_CONTENT);\r\n");
                    controller.append("    }\r\n");
                }
            }
        }

        //=========================================================================================================================
        

        controller.append("}\r\n");

        return controller.toString();
    }

}
