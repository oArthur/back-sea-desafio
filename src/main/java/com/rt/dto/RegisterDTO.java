package com.rt.dto;

import com.rt.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
