package com.dashibo.be.auth.controller;

import com.dashibo.be.auth.model.AuthRequest;
import com.dashibo.be.common.dao.UserDao;
import com.dashibo.be.common.jwt.JwtUtils;
import com.dashibo.be.common.model.CustomUser;
import com.dashibo.be.auth.model.RegisterRequest;
import com.dashibo.be.auth.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UsersController {

    private final RegisterService registerService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDao userDao;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.status(registerService.registerUser(request)).build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );

        final UserDetails user = userDao.findById(request.getUsername()).orElseThrow(()->new UsernameNotFoundException("USER NOT FOUND"));

        if (user!= null)
        {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED ACCESS");
        }

    }

    @GetMapping("/all-users")
    public ResponseEntity<List<CustomUser>> getAllUsers()
    {
        return ResponseEntity.status(HttpStatus.OK).body(registerService.getAllUsers());
    }
}
