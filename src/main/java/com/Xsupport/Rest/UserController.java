package com.Xsupport.Rest;

import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Dto.User.UserUpdateDTO;
import com.Xsupport.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserController {

    User getCurrentUser();

    ResponseEntity<List<UserDTO>> findAll();

    ResponseEntity<Optional<User>> findByUsername(String username);

    ResponseEntity<UserDTO> updateUser(UserUpdateDTO request);
}
