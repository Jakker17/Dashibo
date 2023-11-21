package com.dashibo.be.auth.controller;

import com.dashibo.be.auth.model.CustomUser;
import com.dashibo.be.auth.model.RegisterRequest;
import com.dashibo.be.auth.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UsersController {

    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.status(registerService.registerUser(request)).build();
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<CustomUser>> getAllUsers()
    {
        return ResponseEntity.status(HttpStatus.OK).body(registerService.getAllUsers());
    }
}
