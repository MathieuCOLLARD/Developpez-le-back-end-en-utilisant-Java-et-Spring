package com.openclassrooms.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RentalDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String surface;

    @NotEmpty
    private String price;

    @NotNull
    private MultipartFile picture;

    @NotEmpty
    private String description;
}
