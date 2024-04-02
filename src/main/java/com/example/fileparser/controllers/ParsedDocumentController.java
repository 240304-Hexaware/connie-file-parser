package com.example.fileparser.controllers;

import com.example.fileparser.services.ParsedDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
public class ParsedDocumentController {
    private ParsedDocumentService parsedDocumentService;

    @Autowired
    public ParsedDocumentController(ParsedDocumentService parsedDocumentService) {
        this.parsedDocumentService = parsedDocumentService;
    }

}
