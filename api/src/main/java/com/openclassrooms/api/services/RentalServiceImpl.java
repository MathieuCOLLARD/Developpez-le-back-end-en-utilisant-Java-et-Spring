package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.RentalDTO;
import com.openclassrooms.api.entity.RentalEntity;
import com.openclassrooms.api.repository.RentalRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Data
@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createRental(RentalDTO rentalDTO) {

        RentalEntity rental = modelMapper.map(rentalDTO, RentalEntity.class);
        String uniqueFileName = UUID.randomUUID() + "-" + rentalDTO.getPicture().getOriginalFilename();
        Path path = FileSystems.getDefault().getPath("src/main/java/com/openclassrooms/api/uploads/" + uniqueFileName);
        try {
            Files.write(path, rentalDTO.getPicture().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        URL fileUrl;
        try {
            fileUrl = path.toUri().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        rental.setPicture(fileUrl.toString());
        rentalRepository.save(rental);
        return "Rental created !";
    }
}
