package com.Xsupport.Service.Impl;

import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Dto.User.UserRegistrationDTO;
import com.Xsupport.Dto.User.UserUpdateDTO;
import com.Xsupport.Entity.Role;
import com.Xsupport.Entity.User;
import com.Xsupport.Exception.ExceptionMessage;
import com.Xsupport.Repo.UserRepository;
import com.Xsupport.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;


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
                .updatedTime(LocalDateTime.now())
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
    public UserDTO updateUser(UserUpdateDTO request) {
        User currentUser = getCurrentUser();

        if (currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException(ExceptionMessage.UNAUTHORIZED_ACTION.getMessage());
        }

        User user = repo.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        ExceptionMessage.RECORD_NOT_FOUND.getMessage() + " " + request.getId()
                ));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        user.setUpdatedTime(LocalDateTime.now());
        User savedUser = repo.save(user);
        return mapToDTO(savedUser);
    }


    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return findByEmail(email).orElseThrow(() -> new RuntimeException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
    }


    @Override
    public void setLastLogin(User user) {
        user.setLastLogin(LocalDateTime.now());
        repo.save(user);
    }

    @Override
    public List<UserDTO> findAll() {
        User currentUser = getCurrentUser();

        if (currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException(ExceptionMessage.UNAUTHORIZED_ACTION.getMessage());
        }

        List<User> userList = repo.findAll();
        return userList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}