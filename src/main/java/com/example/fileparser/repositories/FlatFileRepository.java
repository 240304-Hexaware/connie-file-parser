package com.example.fileparser.repositories;

import com.example.fileparser.models.FlatFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlatFileRepository extends MongoRepository<FlatFile, String>  {

    // find all files matching fileName in system
    Optional<List<FlatFile>> findByFileName (String fileName);

    // find all files uploaded by a specific user, identified by uploader id
    // might have to modify this
    Optional<List<FlatFile>> findByUserId(String uploaderId);
}
