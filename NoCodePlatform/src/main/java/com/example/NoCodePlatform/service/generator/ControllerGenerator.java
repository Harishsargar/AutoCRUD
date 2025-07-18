package com.example.NoCodePlatform.service.generator;

import com.example.NoCodePlatform.helper.StringHelper;

public class ControllerGenerator {
    public static String generate(String entityName){
        String name = StringHelper.fileNameCompatible(entityName);
        String camelName = StringHelper.camelCase(entityName);
        StringBuilder controller = new StringBuilder();
        
        controller.append("package com.example.baseProject.controller;\r\n\n");
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

        controller.append("import com.example.baseProject.entity."+name+";\r\n");
        controller.append("import com.example.baseProject.service."+name+"Service;\r\n\n");


 controller.append("@RestController\r\n");
 controller.append("@RequestMapping(\"/api/"+camelName+"\")\r\n");
 controller.append("public class "+name+"Controller {\r\n\n");
 

 controller.append("    @Autowired\r\n");
 controller.append("    private "+name+"Service "+camelName+"Service;\r\n\n");
 
 controller.append("    // create "+camelName+"\r\n");
 controller.append("    @PostMapping(\"/create"+camelName+"\")\r\n");
 controller.append("    public ResponseEntity<"+name+"> create"+name+"(@RequestBody "+name+" "+camelName+"){\r\n");
 controller.append("        "+name+" saved"+name+" = "+camelName+"Service.create"+name+"("+camelName+");\r\n");
 controller.append("        return new ResponseEntity<>(saved"+name+", HttpStatus.CREATED);\r\n");
 controller.append("    }\r\n\n");
 
 controller.append("    // get "+camelName+"\r\n");
 controller.append("    @GetMapping(\"/get"+camelName+"/{id}\")\r\n");
 controller.append("    public ResponseEntity<"+name+"> get"+name+"(@PathVariable String id){\r\n");
 controller.append("        "+name+" "+camelName+" = "+camelName+"Service.get"+name+"byId(id);\r\n");
 controller.append("        return new ResponseEntity<>("+camelName+", HttpStatus.OK);\r\n");
 controller.append("    }\r\n\n");

 controller.append("    // update "+camelName+"\r\n");
 controller.append("    @PutMapping(\"/update"+camelName+"\")\r\n");
 controller.append("    public ResponseEntity<"+name+"> update"+name+"(@RequestBody "+name+" "+camelName+"){\r\n");
 controller.append("        "+name+" updated"+name+" = "+camelName+"Service.update"+name+"("+camelName+");\r\n");
 controller.append("        return new ResponseEntity<>(updated"+name+", HttpStatus.OK);\r\n");
 controller.append("    }\r\n\n");
 
 controller.append("    // delete "+camelName+"\r\n");
 controller.append("    @DeleteMapping(\"/delete"+camelName+"/{id}\")\r\n");
 controller.append("    public ResponseEntity<Void> delete"+name+"(@PathVariable String id){\r\n");
 controller.append("        "+camelName+"Service.delete"+name+"ById(id);\r\n");
 controller.append("        return new ResponseEntity<>(HttpStatus.NO_CONTENT);\r\n");
 controller.append("    }\r\n");

  
 controller.append("}\r\n");




        return controller.toString();
    }

}
