package com.carrot.samples.springbootcrudrepository.controllers;

import com.carrot.samples.springbootcrudrepository.exceptions.ServiceClientException;
import com.carrot.samples.springbootcrudrepository.models.UserCountResponseDto;
import com.carrot.samples.springbootcrudrepository.models.UserDto;
import com.carrot.samples.springbootcrudrepository.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> createNewUser(@RequestBody UserDto context) {

        try {

            if (context == null)
                throw new ServiceClientException("The passed user is not valid for sign up");

            String userId = userService.createUser(context);

            if (userId == null)
                ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

            return ResponseEntity.created(URI.create("http://127.0.0.1:8080/users/" + userId)).build();

        } catch (ServiceClientException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("/users/count")
    public ResponseEntity<?> getUsersCount() {

        UserCountResponseDto userCount = userService.userCount();

        if (userCount == null)
            ResponseEntity.noContent().build();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userCount);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {

        Collection<UserDto> users = userService.getUsers();

        if (users == null)
            ResponseEntity.noContent().build();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getAllUsers(@PathVariable("userId") String userId) {

        try {

            UserDto user = userService.getUsers(userId);

            if (user == null)
                ResponseEntity.noContent().build();

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);

        } catch (ServiceClientException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        }
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @RequestBody UserDto context) {

        try {

            if (context == null)
                throw new ServiceClientException("The passed user is not valid");

            boolean wasSuccessful = userService.updateUser(userId, context);

            if (!wasSuccessful)
                ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

            return ResponseEntity.ok().build();

        } catch (ServiceClientException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) {

        try {

            boolean wasSuccessful = userService.deleteUser(userId);

            if (!wasSuccessful)
                ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

            return ResponseEntity.ok().build();


        } catch (ServiceClientException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        }
    }
}
