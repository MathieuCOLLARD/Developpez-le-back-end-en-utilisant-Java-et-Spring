package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.RentalDTO;
import com.openclassrooms.api.response.MessageResponse;
import com.openclassrooms.api.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/rentals")
    public String getRentals() {
        return "List of rentals";
    }

    @GetMapping("/rentals/:id")
    public String getRental() {
        return "Rental";
    }

    @PostMapping("/rentals")
    public ResponseEntity<MessageResponse> createRental(@ModelAttribute RentalDTO rentalDTO) {
        return ResponseEntity.ok(new MessageResponse(rentalService.createRental(rentalDTO)));
    }

    @PutMapping("/rentals/:id")
    public String updateRental() {
        return "Rental updated";
    }
}
