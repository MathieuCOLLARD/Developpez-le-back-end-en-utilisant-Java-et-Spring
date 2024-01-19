package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.RentalDTO;
import com.openclassrooms.api.entity.RentalEntity;
import com.openclassrooms.api.response.MessageResponse;
import com.openclassrooms.api.response.RentalsResponse;
import com.openclassrooms.api.services.RentalService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/rentals")
    public ResponseEntity<RentalsResponse> getRentals() {
        return ResponseEntity.ok(new RentalsResponse(rentalService.getRentals()));
    }

    @GetMapping("/rentals/{id}")
    public ResponseEntity<RentalDTO> getRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @PostMapping("/rentals")
    public ResponseEntity<MessageResponse> createRental(@ModelAttribute RentalDTO rentalDTO, Principal principalUser, HttpServletRequest request) throws IOException {
        return ResponseEntity.ok(new MessageResponse(rentalService.createRental(rentalDTO, principalUser, request)));
    }

    @PutMapping("/rentals/{id}")
    public ResponseEntity<MessageResponse> updateRental(@PathVariable Long id, @ModelAttribute RentalDTO rentalUpdate) {
        return ResponseEntity.ok(new MessageResponse(rentalService.updateRental(id, rentalUpdate)));
    }
}
