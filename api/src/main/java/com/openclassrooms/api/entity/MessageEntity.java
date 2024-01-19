package com.openclassrooms.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer rental_id;

    private Integer user_id;

    private String message;

    private Timestamp created_at;

    private Timestamp updated_at;

}
