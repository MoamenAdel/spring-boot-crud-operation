package com.example.springbootcrudoperation.controller;


import com.example.springbootcrudoperation.dto.GeneralResponse;
import com.example.springbootcrudoperation.exception.BusinessExceptions;
import com.example.springbootcrudoperation.service.UserService;
import com.example.springbootcrudoperation.validation.AddUserRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<GeneralResponse> list(Pageable pageable) {
        return userService.list(pageable);
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> addNewUser(@RequestBody AddUserRequest request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws BusinessExceptions {
        ResponseEntity<GeneralResponse> response = userService.addNewUser(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
//            new SecurityContextLogoutHandler().logout(httpRequest, httpResponse, authentication);
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
            new SecurityContextLogoutHandler().isInvalidateHttpSession();
            // and the token should be added as a blcklist
        }
        return response;
    }
}
