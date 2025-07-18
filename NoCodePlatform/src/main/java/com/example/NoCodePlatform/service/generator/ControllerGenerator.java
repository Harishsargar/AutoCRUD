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
 controller.append("        User savedUser = userService.createUser(user);\r\n");
 controller.append("        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);\r\n");
 controller.append("    }\r\n\n");
 
 controller.append("    // get user\r\n");
 controller.append("    @GetMapping(\"/getuser/{id}\")\r\n");
 controller.append("    public ResponseEntity<User> getUser(@PathVariable String id){\r\n");
 controller.append("        User user = userService.getUserbyId(id);\r\n");
 controller.append("        return new ResponseEntity<>(user, HttpStatus.OK);\r\n");
 controller.append("    }\r\n\n");

 controller.append("    // update user\r\n");
 controller.append("    @PutMapping(\"/updateuser\")\r\n");
 controller.append("    public ResponseEntity<User> updateUser(@RequestBody User user){\r\n");
 controller.append("        User updatedUser = userService.updateUser(user);\r\n");
 controller.append("        return new ResponseEntity<>(updatedUser, HttpStatus.OK);\r\n");
 controller.append("    }\r\n\n");
 
 controller.append("    // delete user\r\n");
 controller.append("    @DeleteMapping(\"/deleteuser/{id}\")\r\n");
 controller.append("    public ResponseEntity<Void> deleteUser(@PathVariable String id){\r\n");
 controller.append("        userService.deleteUserById(id);\r\n");
 controller.append("        return new ResponseEntity<>(HttpStatus.NO_CONTENT);\r\n");
 controller.append("    }\r\n");

  
 controller.append("}\r\n");




        return controller.toString();
    }

}
