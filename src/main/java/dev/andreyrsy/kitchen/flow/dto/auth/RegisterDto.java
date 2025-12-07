package dev.andreyrsy.kitchen.flow.dto.auth;

import dev.andreyrsy.kitchen.flow.model.user.UserRole;

public record RegisterDto(String login, String password, UserRole role) {
}
