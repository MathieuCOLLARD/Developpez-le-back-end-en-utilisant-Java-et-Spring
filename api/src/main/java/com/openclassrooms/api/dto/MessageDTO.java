package com.openclassrooms.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDTO {

    @NotEmpty
    private String message;

    @NotNull
    private Long user_id;

    @NotNull
    private Long rental_id;
}
