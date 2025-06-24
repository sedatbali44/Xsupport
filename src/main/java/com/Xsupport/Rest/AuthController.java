package com.Xsupport.Rest;

import com.Xsupport.Dto.Login.LoginRequest;
import com.Xsupport.Dto.Login.LoginResponse;
import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Dto.User.UserRegistrationDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


public interface AuthController {

    ResponseEntity<LoginResponse> login(LoginRequest request);

    ResponseEntity<UserDTO> register(UserRegistrationDTO request);

    ResponseEntity<String> logout(HttpServletRequest request);
}
