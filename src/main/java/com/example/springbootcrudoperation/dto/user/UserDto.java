package com.example.springbootcrudoperation.dto.user;

import com.example.springbootcrudoperation.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Integer userId;

    private String username;

    private String email;
    public UserDto(Users user){
        setUserId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
    }

}
