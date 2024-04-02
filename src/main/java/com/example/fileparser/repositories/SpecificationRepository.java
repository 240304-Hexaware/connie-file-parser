package com.example.fileparser.repositories;

import com.example.fileparser.models.FlatFile;
import com.example.fileparser.models.SpecificationFile;
import com.example.fileparser.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecificationRepository extends MongoRepository<SpecificationFile, String>{
    Optional<SpecificationFile> findByName (String name);

}
