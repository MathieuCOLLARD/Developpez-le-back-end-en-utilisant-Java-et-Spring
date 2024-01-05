package com.openclassrooms.api.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RentalDTO {

    private String name;

    private String surface;

    private String price;

    private MultipartFile picture;

    private String description;

    private Integer owner_id;
}
