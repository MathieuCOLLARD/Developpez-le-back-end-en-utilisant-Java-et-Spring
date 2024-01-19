package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.RentalDTO;
import com.openclassrooms.api.entity.RentalEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.security.Principal;

public interface RentalService {

    /**
     * @param rentalDTO
     * @param principalUser
     * @param request
     * @return String
     * @throws IOException
     */
    String createRental(RentalDTO rentalDTO, Principal principalUser, HttpServletRequest request) throws IOException;

    /**
     * @return Object
     */
    Object getRentals();

    /**
     * @param id
     * @return RentalDTO
     */
    RentalDTO getRentalById(Long id);

    /**
     * @param id
     * @return String
     */
    String updateRental(Long id, RentalDTO rentalUpdate);
}
