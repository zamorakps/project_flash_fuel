// // package com.flashfuel.project.controller;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import com.flashfuel.project.model.FuelQuoteRequest;
// // import com.flashfuel.project.model.FuelQuoteResponse;
// // import com.flashfuel.project.service.FuelQuoteService;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;

// // import java.util.List;

// // @RestController
// // @RequestMapping("/api")
// // public class FuelQuoteController {

// //     private FuelQuoteService fuelQuoteService;

// //     @Autowired
// //     public FuelQuoteController(FuelQuoteService fuelQuoteService) {
// //         this.fuelQuoteService = fuelQuoteService;
// //     }

// //     @PostMapping("/fuelquote/new")
// //     public ResponseEntity<String> addFuelQuote(@ModelAttribute FuelQuoteRequest fuelQuoteRequest, @RequestParam Double suggestedPrice, @RequestParam Double totalAmountDue) {
// //         String result = fuelQuoteService.addFuelQuote(fuelQuoteRequest, suggestedPrice, totalAmountDue);
// //         if(result == null)
// //             return ResponseEntity.ok("Fuel quote added successfully.");
// //         else
// //             return ResponseEntity.badRequest().body(result);
// //     }

// //     @GetMapping("/fuelquote/history")
// //     public ResponseEntity<List<FuelQuoteResponse>> getFuelQuoteHistory(@RequestParam String username) {
// //         return ResponseEntity.ok(fuelQuoteService.getFuelQuoteHistory(username));
// //     }
// // }

// package com.flashfuel.project.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import com.flashfuel.project.model.FuelQuoteRequest;
// import com.flashfuel.project.model.FuelQuoteResponse;
// import com.flashfuel.project.service.FuelQuoteService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api")
// public class FuelQuoteController {

//     private FuelQuoteService fuelQuoteService;

//     @Autowired
//     public FuelQuoteController(FuelQuoteService fuelQuoteService) {
//         this.fuelQuoteService = fuelQuoteService;
//     }

//     @PostMapping("/fuelquote/new")
//     public ResponseEntity<String> addFuelQuote(@RequestBody FuelQuoteRequest fuelQuoteRequest) {
//         Double suggestedPrice = 2.0;
//         Double totalAmountDue = suggestedPrice * fuelQuoteRequest.getGallonsRequested();

//         String result = fuelQuoteService.addFuelQuote(fuelQuoteRequest, suggestedPrice, totalAmountDue);
//         if(result == null)
//             return ResponseEntity.ok("Fuel quote added successfully.");
//         else
//             return ResponseEntity.badRequest().body(result);
//     }

//     @GetMapping("/fuelquote/history")
//     public ResponseEntity<List<FuelQuoteResponse>> getFuelQuoteHistory(@RequestParam String username) {
//         return ResponseEntity.ok(fuelQuoteService.getFuelQuoteHistory(username));
//     }
// }


/*
package com.flashfuel.project.controller;

import com.flashfuel.project.model.FuelQuoteRequest;
import com.flashfuel.project.model.FuelQuoteResponse;
import com.flashfuel.project.model.UserProfileResponse;
import com.flashfuel.project.service.FuelQuoteService;
import com.flashfuel.project.service.ProfileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FuelQuoteController {

    private final FuelQuoteService fuelQuoteService;
    private final ProfileManagementService profileManagementService;

    @Autowired
    public FuelQuoteController(FuelQuoteService fuelQuoteService, ProfileManagementService profileManagementService) {
        this.fuelQuoteService = fuelQuoteService;
        this.profileManagementService = profileManagementService;
    }

    @PostMapping("/fuelquote/new")
    public ResponseEntity<String> addFuelQuote(@RequestBody FuelQuoteRequest fuelQuoteRequest) {
        Double suggestedPrice = 2.0;
        Double totalAmountDue = suggestedPrice * fuelQuoteRequest.getGallonsRequested();

        String result = fuelQuoteService.addFuelQuote(fuelQuoteRequest, suggestedPrice, totalAmountDue);
        if(result == null)
            return ResponseEntity.ok("Fuel quote added successfully.");
        else
            return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/fuelquote/history")
    public ResponseEntity<List<FuelQuoteResponse>> getFuelQuoteHistory(@RequestParam String username) {
        return ResponseEntity.ok(fuelQuoteService.getFuelQuoteHistory(username));
    }

    @GetMapping("/user/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(@RequestParam String username) {
        return ResponseEntity.ok(profileManagementService.getProfile(username));
    }
}
*/

package com.flashfuel.project.controller;

import com.flashfuel.project.model.FuelQuoteRequest;
import com.flashfuel.project.model.FuelQuoteResponse;
import com.flashfuel.project.model.UserProfileResponse;
import com.flashfuel.project.service.FuelQuoteService;
import com.flashfuel.project.service.ProfileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FuelQuoteController {

    private final FuelQuoteService fuelQuoteService;
    private final ProfileManagementService profileManagementService;

    @Autowired
    public FuelQuoteController(FuelQuoteService fuelQuoteService, ProfileManagementService profileManagementService) {
        this.fuelQuoteService = fuelQuoteService;
        this.profileManagementService = profileManagementService;
    }

@PostMapping("/fuelquote/new")
public ResponseEntity<Map<String, Object>> addFuelQuote(@RequestBody FuelQuoteRequest fuelQuoteRequest) {
    Double suggestedPrice = 2.0;
    Double totalAmountDue = suggestedPrice * fuelQuoteRequest.getGallonsRequested();

    String result = fuelQuoteService.addFuelQuote(fuelQuoteRequest, suggestedPrice, totalAmountDue);

    Map<String, Object> response = new HashMap<>();
    if(result == null) {
        response.put("message", "Fuel quote added successfully.");
        response.put("suggestedPrice", suggestedPrice);
        response.put("totalAmountDue", totalAmountDue);
        return ResponseEntity.ok(response);
    }
    else {
        response.put("message", result);
        return ResponseEntity.badRequest().body(response);
    }
}

    @GetMapping("/fuelquote/history")
    public ResponseEntity<List<FuelQuoteResponse>> getFuelQuoteHistory(@RequestParam String username) {
        return ResponseEntity.ok(fuelQuoteService.getFuelQuoteHistory(username));
    }

    @GetMapping("/user/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(@RequestParam String username) {
        return ResponseEntity.ok(profileManagementService.getProfile(username));
    }
}