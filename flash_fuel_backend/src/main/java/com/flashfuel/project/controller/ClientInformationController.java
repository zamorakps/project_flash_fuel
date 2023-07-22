package com.flashfuel.project.controller;

import com.flashfuel.project.config.TokenProvider;
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
    public ResponseEntity<?> updateClientInformation(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ClientInformation clientInformation) {
        try {
            TokenProvider tokenProvider = new TokenProvider();
            String jwtToken = authorizationHeader.substring(7);
            clientInformationService.updateClientInformation(tokenProvider.getIdFromToken(jwtToken), clientInformation);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/profile")
    public ResponseEntity<ClientInformation> getClientInformation(@RequestHeader("Authorization") String authorizationHeader) {
        TokenProvider tokenProvider = new TokenProvider();
        String jwtToken = authorizationHeader.substring(7);
        return ResponseEntity.ok(clientInformationService.getClientInformationByUserId(tokenProvider.getIdFromToken(jwtToken)));
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

