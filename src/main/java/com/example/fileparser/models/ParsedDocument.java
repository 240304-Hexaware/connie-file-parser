package com.example.fileparser.models;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

// Generic Parsed Document
@Document("parsed-documents")
public class ParsedDocument extends org.bson.Document {
    @Field(name = "_id")
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field(name = "userId")
    private String userId;

    @Field(name = "specName")
    private String specName;


    // Generic Parsed Document
    public ParsedDocument(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
