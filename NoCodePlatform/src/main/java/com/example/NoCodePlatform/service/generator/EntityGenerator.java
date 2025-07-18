package com.example.NoCodePlatform.service.generator;

import java.util.List;

import com.example.NoCodePlatform.helper.StringHelper;
import com.example.NoCodePlatform.model.AttributesDetails;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.val;

public class EntityGenerator {

    public static String generate(String entityName, List<AttributesDetails> attributesDetails) {
        StringBuilder entity = new StringBuilder();
        String name = StringHelper.fileNameCompatible(entityName); // format HarishSargar
        String camelName = StringHelper.camelCase(entityName); // format harishSargar


        entity.append("package com.example.baseProject.entity;\r\n");

        entity.append("import jakarta.persistence.Column;\r\n");
        entity.append("import jakarta.persistence.Entity;\r\n");
        entity.append("import jakarta.persistence.Id;\r\n");
        entity.append("import lombok.Data;\r\n");

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

        entity.append("}\r\n");

        return entity.toString();
    }
}
