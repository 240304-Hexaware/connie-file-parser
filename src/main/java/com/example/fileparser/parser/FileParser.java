package com.example.fileparser.parser;

import com.example.fileparser.models.Field;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.BsonDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class FileParser {

    /*** Parses a specification JSON file into a map of tokens
     *
     * @param specificationFile - the specification JSON file
     * @return a map of tokens, mapping field name to Field
     * @throws IOException
     */
    public static Map<String, Field> parseSpec(File specificationFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Field> map = mapper.readValue(specificationFile, new TypeReference<Map<String, Field>>() {
        });

        Set<String> fieldNames = map.keySet();
        for (String fieldName : fieldNames) {
            map.get(fieldName).setName(fieldName);
        }
        return map;
    }

    /**
     * This reads the entire file at once with readString.
     * It is not intended for reading in large files.
     *
     * @param file - the flat file we want to read
     * @return - the String representation of the file
     * @throws IOException
     */
    public String readFile(File file) throws IOException {
        // same as readAllBytes
        return Files.readString(file.toPath());
    }

    /**
     * This will take in a File and read the contents character by character, appending to a string before
     * returning the completed string.
     *
     * @param file - the flat file we want to read
     * @return - the String representation of the file
     * @throws IOException
     */
    public String readCompleteChars(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            builder.append((char) reader.read());
        }
        return builder.toString();
    }


    /**
     * Takes the String representation of a flat file and a specification map and
     * creates a list of strings, each String corresponding to one field value
     * from the flat file.
     *
     * @param flatFileData     - the String representation of a flat file
     * @param specificationMap - the map of tokens, mapping field name to Field
     * @return A list of strings, with each string representing one field value from the flat file
     * @throws IOException
     */
    public List<String> readStringFields(String flatFileData, Map<String, Field> specificationMap) throws IOException {
        List<String> fieldValuesList = new ArrayList<>();

        Set<String> fieldNames = specificationMap.keySet();
        for (String fieldName : fieldNames) {
            Field field = specificationMap.get(fieldName);
            // remove whitespace
            String fieldValue = flatFileData.substring(field.getStartPos(), field.getEndPos() + 1).trim();
            fieldValuesList.add(fieldValue);
            System.out.println("[" + fieldName + "][" + fieldValue + "]");
        }
        return fieldValuesList;
    }

    /**
     * Takes the String representation of a flat file and a specification map and
     * returns the string representation of a JSON file
     *
     * @param flatFileData     - the String representation of a flat file
     * @param specificationMap - the map of tokens, mapping field name to Field
     * @return the JSON string
     * @throws IOException
     */
    public List<String> parseFileToJson(String flatFileData, Map<String, Field> specificationMap) throws IOException {
        // maps field name to field value

        List list = new ArrayList<String>();

        Set<String> fieldNames = specificationMap.keySet();
        int offset = 0;
        int cursor = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        while (cursor < flatFileData.length()-1) {
            Map<String, String> dataMap = new HashMap<>();
            for (String fieldName : fieldNames) {
                // get the Field with startPos and endPos from spec map
                Field field = specificationMap.get(fieldName);
                // remove whitespace
                String fieldValue = flatFileData.substring(field.getStartPos() + offset, field.getEndPos() + offset + 1).trim();
                dataMap.put(fieldName, fieldValue);
                cursor = field.getEndPos()+offset+1;
            }
            offset = cursor;
            list.add(objectMapper.writeValueAsString(dataMap));
        }
        // convert hashmap to json string and return
        return list;
    }

}

