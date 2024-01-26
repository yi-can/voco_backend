package com.voco_task.controller;


import com.voco_task.dto.LoginRequestDto;
import com.voco_task.dto.RegisterRequestRestaurantDto;
import com.voco_task.dto.RegisterRequestUserDto;
import com.voco_task.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/user")
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestUserDto dto){
        return ResponseEntity.ok(authService.registerUser(dto));
    }

    @PostMapping("/register/restaurant")
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestRestaurantDto dto){
        return ResponseEntity.ok(authService.registerRestaurant(dto));
    }

    @PostMapping("/register/login")
    public ResponseEntity<String> register(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }
}
