package com.muneesh.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class SignupRequest {
    private String username;
    private String email;
    private String Password;
    private String role;

}

