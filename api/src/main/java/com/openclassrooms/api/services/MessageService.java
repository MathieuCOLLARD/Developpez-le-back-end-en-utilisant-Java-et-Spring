package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.MessageDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;

public interface MessageService {

    /**
     * @param messageDTO
     * @return String
     */
    String createMessage(MessageDTO messageDTO);
}
