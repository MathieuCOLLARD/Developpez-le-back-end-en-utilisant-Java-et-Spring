package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.RentalDTO;
import com.openclassrooms.api.entity.RentalEntity;
import com.openclassrooms.api.response.MessageResponse;
import com.openclassrooms.api.response.RentalsResponse;
import com.openclassrooms.api.services.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all rentals", description = "Return all rentals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rentals found",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)})
    @GetMapping("/rentals")
    public ResponseEntity<RentalsResponse> getRentals() {
        return ResponseEntity.ok(new RentalsResponse(rentalService.getRentals()));
    }

    @Operation(summary = "Get a rental by id", description = "Return a rental by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental found",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)})
    @GetMapping("/rentals/{id}")
    public ResponseEntity<RentalDTO> getRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @Operation(summary = "Create a new rental", description = "Return the rental created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental created",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)})
    @PostMapping("/rentals")
    public ResponseEntity<MessageResponse> createRental(@ModelAttribute RentalDTO rentalDTO, Principal principalUser, HttpServletRequest request) throws IOException {
        return ResponseEntity.ok(new MessageResponse(rentalService.createRental(rentalDTO, principalUser, request)));
    }

    @Operation(summary = "Update a rental", description = "Return the rental updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)})
    @PutMapping("/rentals/{id}")
    public ResponseEntity<MessageResponse> updateRental(@PathVariable Long id, @ModelAttribute RentalDTO rentalUpdate) {
        return ResponseEntity.ok(new MessageResponse(rentalService.updateRental(id, rentalUpdate)));
    }
}
