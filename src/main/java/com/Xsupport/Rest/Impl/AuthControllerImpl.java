package com.Xsupport.Rest.Impl;


import com.Xsupport.Dto.Login.LoginRequest;
import com.Xsupport.Dto.Login.LoginResponse;
import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Dto.User.UserRegistrationDTO;
import com.Xsupport.Rest.AuthController;
import com.Xsupport.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    @Autowired
    private AuthService authService;

    @Override
    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> register(@RequestBody UserRegistrationDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Override
    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
