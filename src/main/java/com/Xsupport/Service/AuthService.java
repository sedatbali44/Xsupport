package com.Xsupport.Service;

import com.Xsupport.Dto.Login.LoginRequest;
import com.Xsupport.Dto.Login.LoginResponse;
import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Dto.User.UserRegistrationDTO;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    UserDTO register(UserRegistrationDTO request);
}
