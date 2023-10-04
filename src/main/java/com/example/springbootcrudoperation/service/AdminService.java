package com.example.springbootcrudoperation.service;

import com.example.springbootcrudoperation.securityConfig.JwtTokenUtil;
import com.example.springbootcrudoperation.securityConfig.JwtUserDetailsService;
import com.example.springbootcrudoperation.dto.GeneralResponse;
import com.example.springbootcrudoperation.dto.admin.AdminDto;
import com.example.springbootcrudoperation.models.Admin;
import com.example.springbootcrudoperation.repository.AdminRepository;
import com.example.springbootcrudoperation.validation.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    private final AdminRepository adminRepository;


    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public ResponseEntity<GeneralResponse> login(LoginRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());

        Admin admin = adminRepository.findByUsernameIgnoreCase(request.getUsername());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new GeneralResponse().response(new AdminDto(admin, token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
