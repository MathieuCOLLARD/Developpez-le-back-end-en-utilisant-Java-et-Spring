package com.openclassrooms.api.response;

import com.openclassrooms.api.dto.UserDTO;
import lombok.Data;

@Data
public class UserResponse {
    private Integer id;

    private String email;

    private String name;

    private String created_at;

    private String updated_at;
}
