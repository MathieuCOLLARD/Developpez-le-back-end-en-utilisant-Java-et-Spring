package com.openclassrooms.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Rentals")
public class RentalEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String name;

        private String surface;

        private String price;

        private String picture;

        private String description;

        private Integer owner_id;

        private Timestamp created_at;

        private Timestamp updated_at;
}
