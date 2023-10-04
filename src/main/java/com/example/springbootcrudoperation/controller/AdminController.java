package com.example.springbootcrudoperation.controller;


import com.example.springbootcrudoperation.dto.GeneralResponse;
import com.example.springbootcrudoperation.exception.BusinessExceptions;
import com.example.springbootcrudoperation.service.AdminService;
import com.example.springbootcrudoperation.validation.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> login(@RequestBody LoginRequest request) throws Exception {
        return adminService.login(request);
    }
}
