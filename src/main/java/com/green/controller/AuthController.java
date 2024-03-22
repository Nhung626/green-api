package com.green.controller;

import com.green.dto.auth.sdi.LoginSdi;
import com.green.dto.auth.sdo.LoginSdo;
import com.green.model.User;
import com.green.repository.UserRepo;
import com.green.security.UserDetailsImpl;
import com.green.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginSdo> login(@RequestBody LoginSdi req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        String role = roles.get(0);
        User user = userRepo.findById(userDetails.getId()).orElseThrow();
        var rs = new LoginSdo(jwt, "Bearer", user.getId(), user.getEmail(), role);
        return ResponseEntity.ok(rs);
    }
}
