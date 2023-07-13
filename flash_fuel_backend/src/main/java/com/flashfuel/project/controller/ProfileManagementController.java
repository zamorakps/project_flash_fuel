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


/*    public static class UserProfileRequest {
        private String username;
        private String name;
        private String address;
        private String addressLine2;
        private String city;
        private String state;
        private String zipCode;

        // Getters and setters for the fields

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String address) {
            this.addressLine2 = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
    public static class UserProfileResponse {
        private String username;
        private String name;
        private String address;
        private String addressLine2;
        private String city;
        private String state;
        private String zipCode;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }*/

}


