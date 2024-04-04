package com.example.fileparser.services;


import com.example.fileparser.exceptions.ItemAlreadyExistsException;
import com.example.fileparser.exceptions.ItemNotFoundException;
import com.example.fileparser.models.User;
import com.example.fileparser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

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

    public User registerUser(String username, String password) throws ItemAlreadyExistsException {
        if (userRepository.existsByUsername(username)){
            throw new ItemAlreadyExistsException("There is already a registered user with username " + username);
        }

        return userRepository.save(new User(username, password));
    }

}
