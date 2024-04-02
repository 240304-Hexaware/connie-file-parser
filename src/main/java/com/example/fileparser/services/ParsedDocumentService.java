package com.example.fileparser.services;

import com.example.fileparser.models.ParsedDocument;
import com.example.fileparser.repositories.ParsedDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParsedDocumentService {
    private ParsedDocumentRepository parsedDocumentRepository;

    @Autowired
    public ParsedDocumentService(ParsedDocumentRepository parsedDocumentRepository) {
        this.parsedDocumentRepository = parsedDocumentRepository;
    }

    public ParsedDocument createParsedDocument(ParsedDocument parsedDocument) {
        return parsedDocumentRepository.save(parsedDocument);
    }

    public List<ParsedDocument> getAllFilesFromUser(String userId){
        return parsedDocumentRepository.findByUserId(userId);
    }

    public List<ParsedDocument> getAllFilesBySpecName (String specName){
        return parsedDocumentRepository.findBySpecName(specName);
    }

}
