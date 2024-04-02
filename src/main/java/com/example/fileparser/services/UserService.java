package com.example.fileparser.services;


import com.example.fileparser.exceptions.ItemNotFoundException;
import com.example.fileparser.models.User;
import com.example.fileparser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // change later
    public User loginUser(String username, String password) throws ItemNotFoundException{
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(() ->
                new ItemNotFoundException("No user with these specifications could be found."));
    }

    // add stuff to handle already existing user
    public User registerUser(String username, String password) {
        User user = new User(username, password);

        //todo: encrypt password

        return userRepository.save(user);
    }
//    public void deleteUserById (String userId){
//        userRepository.deleteById(userId);
//    }

}
