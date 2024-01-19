package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.RentalDTO;
import com.openclassrooms.api.entity.RentalEntity;
import com.openclassrooms.api.repository.RentalRepository;
import com.openclassrooms.api.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;

@Data
@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param rentalDTO
     * @param principalUser
     * @param request
     * @return String
     * @throws IOException
     */
    @Override
    public String createRental(RentalDTO rentalDTO, Principal principalUser, HttpServletRequest request) throws IOException {


        RentalEntity rental = modelMapper.map(rentalDTO, RentalEntity.class);
        rental.setOwner_id(userRepository.findByEmail(principalUser.getName()).getId());
        MultipartFile file = rentalDTO.getPicture();
        String fileName = file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath("/uploads/");

        //Crée un fichier dans le serveur
        File dest = new File(filePath + fileName);
        if(!dest.exists()) {
            new File(filePath).mkdir();
        }

        //On transfère l'image dans le serveur
        file.transferTo(dest);

        String contextPath = request.getContextPath();
        String relativePath = "/uploads/" + fileName;

        //On génère l'URL
        String imageUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + relativePath;
        rental.setPicture(imageUrl);

        rental.setCreated_at(new Timestamp(System.currentTimeMillis()));
        rental.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        rentalRepository.save(rental);
        return "Rental created !";
    }

    /**
     * @return Object
     */
    @Override
    public Object getRentals() {
        return rentalRepository.findAll();
    }

    /**
     * @param id
     * @return RentalDTO
     */
    @Override
    public RentalDTO getRentalById(Long id) {
        rentalRepository.findById(id).get();
        return modelMapper.map(rentalRepository.findById(id).get(), RentalDTO.class);
    }

    /**
     * @param id
     * @return String
     */
    @Override
    public String updateRental(Long id, RentalDTO rentalUpdate) {
        RentalEntity rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
        RentalEntity rentalEntity = modelMapper.map(rentalUpdate, RentalEntity.class);
        rental.setName(rentalEntity.getName());
        rental.setSurface(rentalEntity.getSurface());
        rental.setPrice(rentalEntity.getPrice());
        rental.setDescription(rentalEntity.getDescription());
        rental.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        rentalRepository.save(rental);
        return "Rental updated !";
    }
}
