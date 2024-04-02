package com.example.fileparser.controllers;

import com.example.fileparser.exceptions.ItemNotFoundException;
import com.example.fileparser.models.Field;
import com.example.fileparser.models.FlatFile;
import com.example.fileparser.models.ParsedDocument;
import com.example.fileparser.models.SpecificationFile;
import com.example.fileparser.services.FlatFileService;
import com.example.fileparser.services.ParsedDocumentService;
import com.example.fileparser.services.SpecificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jdk.jfr.ContentType;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@RestController
@CrossOrigin(origins="*")
public class FlatFileController {
    private FlatFileService flatFileService;
    private SpecificationService specificationService;

    private ParsedDocumentService parsedDocumentService;

    @Autowired
    public FlatFileController(FlatFileService flatFileService, SpecificationService specificationService, ParsedDocumentService parsedDocumentService){
        this.flatFileService = flatFileService;
        this.specificationService = specificationService;
        this.parsedDocumentService = parsedDocumentService;
    }

    //upload file
    @PostMapping(value = "/files", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ParsedDocument> uploadFile(@RequestParam ("userId") String userId, @RequestParam ("specName") String specName, @RequestParam ("file") MultipartFile file) throws IOException, ItemNotFoundException {
        // return list of ParsedDocuments

        // upload flat file
        FlatFile flatFile = flatFileService.uploadFlatFile(userId, file);

        // read flat file
        String fileData = flatFileService.readFlatFile(flatFile);

        // get specification file from spec name
        SpecificationFile specFile = specificationService.getSpecificationFile(specName);
        Map<String, Field> specMap = specificationService.parseSpec(specFile);

        List<ParsedDocument> parsedDocs = new ArrayList<>();
        Set<String> fieldNames = specMap.keySet();

        int offset = 0;
        int cursor = 0;
        while(cursor < fileData.length()-1) {
            ParsedDocument pd = new ParsedDocument();
            for (String fieldName : fieldNames) {
                // get the Field with startPos and endPos from spec map
                Field field = specMap.get(fieldName);
                // get the field value from flat file data
                String fieldValue = fileData.substring(field.getStartPos() + offset, field.getEndPos() + 1 + offset).trim();
                pd.append(fieldName, fieldValue);
                cursor = field.getEndPos()+offset+1;
            }
            offset = cursor;
            pd.append("specName", specName);
            pd.append("userId", userId);
            parsedDocumentService.createParsedDocument(pd);
            parsedDocs.add(pd);
        }
        // Parsed documents will have the field names from the spec file, the spec name, and userId as field names

        return parsedDocs;
    }


    @GetMapping("/files/user")
    @ResponseStatus(HttpStatus.OK)
    public List<ParsedDocument> getAllFilesFromUser(@RequestParam("userId") String userId) throws ItemNotFoundException {
        return parsedDocumentService.getAllFilesFromUser(userId);
    }

    @GetMapping("/files/specType")
    @ResponseStatus(HttpStatus.OK)
    public List<ParsedDocument> getAllFilesBySpecName(@RequestParam ("specName") String specName) throws ItemNotFoundException {
        return parsedDocumentService.getAllFilesBySpecName(specName);
    }


    //Exception Handler
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String queryItemNotFound(ItemNotFoundException e) {
        System.out.println(e.getMessage());//we would want to log this instead in the real world
        return e.getMessage();
    }

}
