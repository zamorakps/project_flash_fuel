package com.flashfuel.project.controller;

import com.flashfuel.project.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final UserManager userManager;

    @Autowired
    public LoginController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (userManager.isUserRegistered(username) && userManager.isValidCredentials(username, password)) {
            String token = generateAuthToken(); // Generate a JWT token for authentication
            LoginResponse response = new LoginResponse(token, null);
            return ResponseEntity.ok(response);
        } else {
            // Invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(null, "Unauthorized Access"));
        }
    }

    private String generateAuthToken() {
        // possible for advanced token generation
        return "RandomToken";
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class LoginResponse {
        private String token;
        private String errorMessage;

        public LoginResponse(String token, String errorMessage) {
            this.token = token;
            this.errorMessage = errorMessage;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
