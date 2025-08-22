package com.muneesh.Services;

import com.muneesh.DAO.UserRepository;
import com.muneesh.DTO.LoginRequest;
import com.muneesh.DTO.LoginResponse;
import com.muneesh.DTO.SignupRequest;
import com.muneesh.DTO.SignupResponse;
import com.muneesh.Entity.User;
import com.muneesh.config.Jwt;
import com.muneesh.enu.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private Jwt jwt;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userrepository;
    public SignupResponse register(SignupRequest request /*object of signup dto*/) {
        if (userrepository.existsByUsername(request.getUsername())) {
            return new SignupResponse("User name already exist");
        }
        if(userrepository.existsByEmail(request.getEmail())){
            return new SignupResponse("Email already exist");
        }
        User user = new User();//it is pojo means it's not a spring class that's  why autowired is not used
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(Role.valueOf(request.getRole()));
        user.setPassword(encoder.encode(request.getPassword()));
        userrepository.save(user);
        return new SignupResponse("Sign up success");

    }

    public LoginResponse login(LoginRequest request) {
        User user = userrepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {     //user.getpassword  comes from the db and comapre it
            throw new RuntimeException("inncorrect password");
        }
        String token = jwt.generateToken(user);
        long expiry = jwt.getExpiry();
        return new LoginResponse(token,expiry);
    }
}
