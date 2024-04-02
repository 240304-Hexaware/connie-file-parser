package com.example.fileparser.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("specifications")

public class SpecificationFile {
    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)
    private String specificationId;

    @Field("userId")
    private String userId;

    @Field (name = "filePath")
    private String filePath;

    @Field("name")
    private String name;

    public SpecificationFile(String userId, String filePath, String name) {
        this.userId = userId;
        this.filePath = filePath;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
