package com.demo.Demmo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;


    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
