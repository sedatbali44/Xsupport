package com.Xsupport.Service.Impl;


import com.Xsupport.Dto.Login.LoginRequest;
import com.Xsupport.Dto.Login.LoginResponse;
import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Dto.User.UserRegistrationDTO;
import com.Xsupport.Entity.User;
import com.Xsupport.Exception.ExceptionMessage;
import com.Xsupport.Security.JwtUtil;
import com.Xsupport.Service.AuthService;
import com.Xsupport.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private  JwtUtil jwtUtil;

    @Override
    public UserDTO register(UserRegistrationDTO request) {
        return userService.save(request);
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException(ExceptionMessage.USER_NOT_FOUND.getMessage()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException(ExceptionMessage.INVALID_CREDENTIALS.getMessage());
        }

        String token = jwtUtil.generateToken(user.getEmail());
        user.setLastLogin(LocalDateTime.now());
        UserDTO userDTO = userService.mapToDTO(user);

        return new LoginResponse(token, userDTO);
    }

}
