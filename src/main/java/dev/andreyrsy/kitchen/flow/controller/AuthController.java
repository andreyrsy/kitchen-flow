package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.config.TokenConfig;
import dev.andreyrsy.kitchen.flow.dto.auth.AuthenticationDto;
import dev.andreyrsy.kitchen.flow.dto.auth.LoginResponseDto;
import dev.andreyrsy.kitchen.flow.dto.auth.RegisterDto;
import dev.andreyrsy.kitchen.flow.model.user.User;
import dev.andreyrsy.kitchen.flow.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenConfig tokenConfig;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenConfig tokenConfig) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenConfig.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data){
        if(this.userRepository.findByLogin(data.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }


}
