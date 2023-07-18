package com.flashfuel.project.controller;

import com.flashfuel.project.service.ClientInformationService;
import com.flashfuel.project.model.ClientInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClientInformationController {

    private final ClientInformationService clientInformationService;

    public ClientInformationController(ClientInformationService clientInformationService) {
        this.clientInformationService = clientInformationService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/profile/update")
    public ResponseEntity<?> updateClientInformation(@RequestParam Long userId, @RequestBody ClientInformation clientInformation) {
        try {
            clientInformationService.updateClientInformation(userId, clientInformation);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/profile")
    public ResponseEntity<ClientInformation> getClientInformation(@RequestParam Long userId) {
        return ResponseEntity.ok(clientInformationService.getClientInformationByUserId(userId));
    }
}


/*
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/profile/update")
    public ResponseEntity<?> updateProfile(@ModelAttribute UserProfileRequest profileRequest) {
        return ResponseEntity.ok(_service.updateProfile(profileRequest));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(@RequestParam String username) {
        return ResponseEntity.ok(_service.getProfile(username));
    }
}
*/

