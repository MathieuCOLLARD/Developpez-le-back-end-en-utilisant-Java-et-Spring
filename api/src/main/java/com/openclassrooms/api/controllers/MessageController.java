package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.MessageDTO;
import com.openclassrooms.api.response.MessageResponse;
import com.openclassrooms.api.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Operation(summary = "Create a new message", description = "Return the message created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message created",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)})
    @PostMapping("/messages")
    public ResponseEntity<Object> createMessage(@Valid @RequestBody MessageDTO messageDTO, Errors errors) {
        if(errors.hasErrors()) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new MessageResponse(messageService.createMessage(messageDTO)));
    }
}
