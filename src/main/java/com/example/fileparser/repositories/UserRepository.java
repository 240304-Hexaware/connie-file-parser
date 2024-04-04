package com.example.fileparser.repositories;


import com.example.fileparser.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Boolean existsByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
