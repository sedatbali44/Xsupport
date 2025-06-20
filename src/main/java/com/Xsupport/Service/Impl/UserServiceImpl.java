package com.Xsupport.Service.Impl;

import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Dto.User.UserRegistrationDTO;
import com.Xsupport.Entity.Role;
import com.Xsupport.Entity.User;
import com.Xsupport.Exception.ExceptionMessage;
import com.Xsupport.Repo.UserRepository;
import com.Xsupport.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public UserDTO save(UserRegistrationDTO request) {
        if (findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException(ExceptionMessage.USER_ALREADY_EXISTS.getMessage());
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .createdTime(LocalDateTime.now())
                .build();

        User savedUser = repo.save(user);

        return mapToDTO(savedUser);
    }


    @Override
    public UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdTime(user.getCreatedTime())
                .updatedTime(user.getUpdatedTime())
                .lastLogin(user.getLastLogin())
                .build();
    }

    @Override
    public UserDTO updateUser(UserRegistrationDTO request) {
        User currentUser = getCurrentUser();
        if (currentUser.getRole().equals(Role.ADMIN)) {
            return save(request);
        } else {
            throw new AccessDeniedException(ExceptionMessage.UNAUTHORIZED_ACTION.getMessage());
        }
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return findByEmail(email).orElseThrow(() -> new RuntimeException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }


}
