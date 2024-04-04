package com.example.fileparser.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("flat-files")
public class FlatFile {
    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)//helps spring understand how to utilize mongo's ID objects
    private String fileId;//with that above annotation we could use String in place of ObjectId type.

    @Field(name = "fileName")
    private String fileName;

    @Field(name = "userId")
    private String userId;

    @Field (name = "filePath")
    private String filePath;

    public FlatFile(String fileName, String userId, String filePath) {
        this.fileName = fileName;
        this.userId = userId;
        this.filePath = filePath;

    }

    public String getId() {
        return fileId;
    }

    public void setId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

}
