package com.rodri.dev.cuidar.dto;

import com.rodri.dev.cuidar.entity.User;

public record UserDto(

        Long id,
        String name,
        String email
) {
    public UserDto(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
