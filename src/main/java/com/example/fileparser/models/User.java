package com.example.fileparser.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("users")
public class User {
    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)//helps spring understand how to utilize mongo's ID objects
    private String userId;//with that above annotation we could use String in place of ObjectId type.

    @Field(name = "username")
    private String username;

    @Field(name = "password")
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id;
    }

}