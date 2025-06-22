package com.Xsupport.Rest.Impl;

import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Entity.User;
import com.Xsupport.Rest.UserController;
import com.Xsupport.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;


    @Override
    @GetMapping("/me")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @Override
    @GetMapping("/all-users")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    @GetMapping("/find-byname")
    public ResponseEntity<Optional<User>> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }
}
