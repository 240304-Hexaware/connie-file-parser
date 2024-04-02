package com.example.fileparser.repositories;

import com.example.fileparser.models.ParsedDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParsedDocumentRepository extends MongoRepository<ParsedDocument, String>{

    List<ParsedDocument> findByUserId(String userId);

    List<ParsedDocument> findBySpecName(String specName);
}
