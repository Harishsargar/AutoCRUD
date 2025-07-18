package com.example.NoCodePlatform.model;

import java.util.List;

import lombok.Data;

@Data
public class EntityDetails {
    private String entityName;
    private List<AttributesDetails> attribute;
}

