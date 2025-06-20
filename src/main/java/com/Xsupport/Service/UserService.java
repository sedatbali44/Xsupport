package com.Xsupport.Service;

import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Dto.User.UserRegistrationDTO;
import com.Xsupport.Entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    UserDTO save(UserRegistrationDTO request);

    UserDTO mapToDTO(User user);

    User getCurrentUser();

    UserDTO updateUser(UserRegistrationDTO request);
}
