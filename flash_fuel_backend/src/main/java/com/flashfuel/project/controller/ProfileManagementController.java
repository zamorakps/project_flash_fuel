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
        /*String username = profileRequest.getUsername();

        if (!userManager.isUserRegistered(username)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
        }

        if (profileRequest.getName() == null || profileRequest.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is required.");
        }

        if (profileRequest.getAddress() == null || profileRequest.getAddress().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address is required.");
        }

        if (profileRequest.getCity() == null || profileRequest.getCity().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City is required.");
        }

        if (profileRequest.getState() == null || profileRequest.getState().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("State is required.");
        }

        if (profileRequest.getZipCode() == null || profileRequest.getZipCode().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zip Code is required.");
        } else {
            String zipCodePattern = "^\\d{5}(?:[-\\s]\\d{4})?$";
            if (!profileRequest.getZipCode().matches(zipCodePattern)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Zip Code format.");
            }
        }

        userManager.updateUserCredentials(
                username,
                profileRequest.getName(),
                profileRequest.getAddress(),
                profileRequest.getAddressLine2(),
                profileRequest.getCity(),
                profileRequest.getState(),
                profileRequest.getZipCode()
        );

        return ResponseEntity.ok("Profile updated successfully.");*/
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(@RequestParam String username) {
        /*if (!userManager.isUserRegistered(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }*/

        return ResponseEntity.ok(_service.getProfile(username));

/*        User user = userManager.getUserByUsername(username);

        UserProfileResponse profileResponse = new UserProfileResponse();
        profileResponse.setUsername(user.getUsername());
        profileResponse.setName(user.getName());
        profileResponse.setAddress(user.getAddress());
        profileResponse.setAddressLine2(user.getAddressLine2());
        profileResponse.setCity(user.getCity());
        profileResponse.setState(user.getState());
        profileResponse.setZipCode(user.getZipCode());

        return ResponseEntity.ok(profileResponse);*/
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


