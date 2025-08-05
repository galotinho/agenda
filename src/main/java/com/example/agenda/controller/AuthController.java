package com.example.agenda.controller;

import com.example.agenda.dto.ApiResponse;
import com.example.agenda.dto.LoginRequest;
import com.example.agenda.dto.SignupRequest;
import com.example.agenda.model.User;
import com.example.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody SignupRequest signupRequest) {
        try {
            // Validate input
            if (signupRequest.getNome() == null || signupRequest.getNome().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Nome é obrigatório"));
            }

            if (signupRequest.getEmail() == null || signupRequest.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Email é obrigatório"));
            }

            if (signupRequest.getSenha() == null || signupRequest.getSenha().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Senha é obrigatória"));
            }
            
            User user = userService.registerUser(signupRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Usuário registrado com sucesso"));
                
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "Erro interno do servidor"));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Validate input
            if (loginRequest.getEmail() == null || loginRequest.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Email é obrigatório"));
            }

            if (loginRequest.getSenha() == null || loginRequest.getSenha().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Senha é obrigatória"));
            }
            
            boolean isAuthenticated = userService.authenticateUser(loginRequest);

            if (isAuthenticated) {
                return ResponseEntity.ok(new ApiResponse(true, "Login realizado com sucesso"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(false, "Email ou senha inválidos"));
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "Erro interno do servidor"));
        }
    }
}
