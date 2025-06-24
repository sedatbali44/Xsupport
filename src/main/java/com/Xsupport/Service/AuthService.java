package com.Xsupport.Service;

import com.Xsupport.Dto.Login.LoginRequest;
import com.Xsupport.Dto.Login.LoginResponse;
import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Dto.User.UserRegistrationDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    UserDTO register(UserRegistrationDTO request);

    String logout(HttpServletRequest request);
}
