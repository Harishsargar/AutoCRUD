package com.example.NoCodePlatform.service.generator;

import java.util.List;

import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.model.AttributesDetails;
import com.example.NoCodePlatform.model.RelationDetails;


public class EntityGenerator {

    public static String generate(String entityName, List<AttributesDetails> attributesDetails,
            List<RelationDetails> relationDetails) {
        StringBuilder entity = new StringBuilder();
        String name = StringHelper.fileNameCompatible(entityName); // format HarishSargar
        String camelName = StringHelper.camelCase(entityName); // format harishSargar
        System.out.println(" writing code: "+entityName);
        entity.append("package com.example.baseProject.entity;\r\n\n");

        entity.append("import java.util.List;\r\n\n");
        entity.append("import com.fasterxml.jackson.annotation.JsonIgnore;\r\n");
        entity.append("import jakarta.persistence.CascadeType;\r\n");
        entity.append("import jakarta.persistence.Column;\r\n");
        entity.append("import jakarta.persistence.Entity;\r\n");
        entity.append("import jakarta.persistence.Id;\r\n");
        entity.append("import jakarta.persistence.ManyToOne;\r\n");
        entity.append("import jakarta.persistence.OneToMany;\r\n");
        entity.append("import lombok.Data;\r\n\n");

        entity.append("@Data\r\n");
        entity.append("@Entity\r\n");
        entity.append("public class " + name + " {\r\n");
        entity.append("    @Id\r\n");
        entity.append("    private String " + name + "Id;\r\n");

        for (AttributesDetails attribute : attributesDetails) {
            String value = StringHelper.camelCase(attribute.getName());
            String type = attribute.getType();
            if (!attribute.isNullable()) {
                entity.append("@Column(nullable = false)\r\n");
            }
            entity.append(" private " + type + " " + value + ";\r\n");
        }


        // this hnadles the relation code generation
        // generated code based on the type of the relation type
        System.out.println(" writing relationship code: "+entityName);
        if (relationDetails != null) {
            for (RelationDetails relation : relationDetails) {
                String target = relation.getTargetEntity();
                if (relation.getRelationType().equalsIgnoreCase("onetomany")) {

                    entity.append("@OneToMany(mappedBy = \""+camelName+"\", cascade = CascadeType.ALL, orphanRemoval = true)\r\n");
                    entity.append("private List<"+StringHelper.fileNameCompatible(target)+"> "+StringHelper.camelCase(target)+";\r\n\n");
                    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
                    // private List<Task> task;

                }else if(relation.getRelationType().equalsIgnoreCase("manytoone")){

                    entity.append("@ManyToOne\r\n@JsonIgnore\r\n");
                    entity.append("private "+StringHelper.fileNameCompatible(target)+" "+StringHelper.camelCase(target)+";\r\n");

                    // @ManyToOne
                    // @JsonIgnore
                    // private User user;
                }
            }
        }

        entity.append("}\r\n");

        return entity.toString();
    }
}
