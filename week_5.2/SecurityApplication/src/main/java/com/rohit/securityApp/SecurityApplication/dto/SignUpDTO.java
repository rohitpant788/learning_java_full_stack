package com.rohit.securityApp.SecurityApplication.dto;

import com.rohit.securityApp.SecurityApplication.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {
    private String email;
    private String password;
    private String name ;
    private Set<Role> roles;
}
