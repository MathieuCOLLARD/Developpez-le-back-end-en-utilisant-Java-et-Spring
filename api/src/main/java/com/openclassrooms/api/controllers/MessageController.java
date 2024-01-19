package com.openclassrooms.api.controllers;

import com.openclassrooms.api.dto.MessageDTO;
import com.openclassrooms.api.response.MessageResponse;
import com.openclassrooms.api.services.MessageService;
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

    @PostMapping("/messages")
    public ResponseEntity<Object> createMessage(@Valid @RequestBody MessageDTO messageDTO, Errors errors) {
        if(errors.hasErrors()) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new MessageResponse(messageService.createMessage(messageDTO)));
    }
}
