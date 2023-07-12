package com.flashfuel.project.controller;

import com.flashfuel.project.UserManager;
import com.flashfuel.project.model.User;
import com.flashfuel.project.model.UserProfileRequest;
import com.flashfuel.project.model.UserProfileResponse;
import com.flashfuel.project.service.ProfileManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfileManagementController {

//    private final UserManager userManager;
    private ProfileManagementService _service;

    public ProfileManagementController(ProfileManagementService service) {
        _service = service;
    }

    @PostMapping("/profile/update")
    public ResponseEntity<?> updateProfile(@ModelAttribute UserProfileRequest profileRequest) {
        return ResponseEntity.ok(_service.updateProfile(profileRequest));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(@RequestParam String username) {
        return ResponseEntity.ok(_service.getProfile(username));
    }
}


