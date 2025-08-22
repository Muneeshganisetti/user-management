package com.muneesh.Controller;


import com.muneesh.DTO.LoginRequest;
import com.muneesh.DTO.LoginResponse;
import com.muneesh.DTO.SignupRequest;
import com.muneesh.DTO.SignupResponse;
import com.muneesh.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Auth")
public class AuthController {
    @Autowired
    private AuthService service;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request){
        try{
            SignupResponse response = service.register(request);// this is calling the service class and the service register method
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        LoginResponse response = service.login(request);
        return ResponseEntity.ok(response);
    }
}
