package com.example.fileparser.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
/***
 * Represents one field of a specification file
 */
public class Field {
    private String name;
    private Integer startPos;
    private Integer endPos;
    private String dataType;

    public Field() {
    }

    public Field(String name, Integer startPos, Integer endPos, String dataType) {
        //name of field
        this.name = name;
        //1
        this.startPos = startPos;
        //15
        this.endPos = endPos;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartPos() {
        return startPos;
    }

    @JsonProperty("startPos")
    public void setStartPos(Integer startPos) {
        this.startPos = startPos;
    }

    public Integer getEndPos() {
        return endPos;
    }

    @JsonProperty("endPos")
    public void setEndPos(Integer endPos) {
        this.endPos = endPos;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Token{" +
                "name='" + name + '\'' +
                ", startPos=" + startPos +
                ", endPos=" + endPos +
                ", dataType=" + dataType +
                '}';
    }
}
