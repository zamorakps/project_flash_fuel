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
    private final TokenProvider tokenProvider;

    public ClientInformationController(ClientInformationService clientInformationService, TokenProvider tokenProvider) {
        this.clientInformationService = clientInformationService;
        this.tokenProvider = tokenProvider;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/profile/update")
    public ResponseEntity<?> updateClientInformation(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ClientInformation clientInformation) {
        try {
            //TokenProvider tokenProvider = new TokenProvider();
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
        //TokenProvider tokenProvider = new TokenProvider();
        String jwtToken = authorizationHeader.substring(7);
        return ResponseEntity.ok(clientInformationService.getClientInformationByUserId(tokenProvider.getIdFromToken(jwtToken)));
    }
}