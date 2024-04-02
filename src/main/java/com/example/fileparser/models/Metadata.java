package com.example.fileparser.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.time.LocalDate;
@Document("metadatum")
public class Metadata {
    @Field(name = "_id")
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    //user that uploaded file
    @Field(name = "userId")
    private String userId;

    @Field(name = "uploadDate")
    private LocalDate uploadDate;

    @Field(name = "fileId")
    private String fileId;

    // not sure if I need this
//    @Field(name = "specificationId")
//    private String specificationId;

    @Field(name = "fileSize")
    private long fileSize;

    @Field(name = "fileLocation")
    private String fileLocation;

    public Metadata(){

    }
    public Metadata(String id, String userId, LocalDate uploadDate, String fileId, long fileSize, String fileLocation) {
        this.id = id;
        this.userId = userId;
        this.uploadDate = uploadDate;
        this.fileId = fileId;
        this.fileSize = fileSize;
        this.fileLocation = fileLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

//    public String getSpecificationId() {
//        return specificationId;
//    }
//
//    public void setSpecificationId(String specificationId) {
//        this.specificationId = specificationId;
//    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
