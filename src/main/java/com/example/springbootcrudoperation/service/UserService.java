package com.example.springbootcrudoperation.service;

import com.example.springbootcrudoperation.dto.GeneralResponse;
import com.example.springbootcrudoperation.dto.user.UserDto;
import com.example.springbootcrudoperation.exception.BusinessExceptions;
import com.example.springbootcrudoperation.models.Users;
import com.example.springbootcrudoperation.repository.UserRepository;
import com.example.springbootcrudoperation.validation.AddUserRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<GeneralResponse> list(Pageable pageable) {
        return new GeneralResponse().response(userRepository.findAll(pageable));
    }

    public ResponseEntity<GeneralResponse> addNewUser(AddUserRequest request) throws BusinessExceptions {

        if (userRepository.existsByUsernameIgnoreCase(request.getUsername())) {
            throw new BusinessExceptions("username.exist");
        } else if (userRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new BusinessExceptions("email.exist");
        }

        Users user = new Users(request.getUsername(), request.getEmail());
        userRepository.save(user);

        return new GeneralResponse().response(new UserDto(user));
    }

}
