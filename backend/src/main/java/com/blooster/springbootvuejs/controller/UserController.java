package com.blooster.springbootvuejs.controller;

import com.blooster.springbootvuejs.entity.User;
import com.blooster.springbootvuejs.exception.InternalServerError;
import com.blooster.springbootvuejs.exception.UserNotFound;
import com.blooster.springbootvuejs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // Create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User newuser = new User(user.getFirstName(), user.getLastName(), user.getEmail());
            userRepository.save(newuser);
            return new ResponseEntity<>(newuser, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> userdata = userRepository.findById(id);

        if (userdata.isPresent()) {
            User _user = userdata.get();
            _user.setEmail(user.getEmail());
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            throw new UserNotFound("Invalid User Id");
        }
    }

    // Get all Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        try {
            List<User> users = new ArrayList<>();
            userRepository.findAll().forEach(users::add);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }

    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") Long id) {

        Optional<User> userdata = userRepository.findById(id);

        if (userdata.isPresent()) {
            return new ResponseEntity<>
                    (userdata.get(), HttpStatus.OK);
        } else {
            throw new UserNotFound("Invalid User Id");
        }

    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {

        Optional<User> userdata = userRepository.findById(id);

        if (userdata.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new UserNotFound("Invalid User Id");
        }
    }
}
