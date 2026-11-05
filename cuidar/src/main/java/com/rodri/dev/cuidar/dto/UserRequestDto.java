package com.rodri.dev.cuidar.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {
        @Override
        public String toString() {
                return "UserRequestDTO [email = " + email + " password = xxx]";
        }
}
