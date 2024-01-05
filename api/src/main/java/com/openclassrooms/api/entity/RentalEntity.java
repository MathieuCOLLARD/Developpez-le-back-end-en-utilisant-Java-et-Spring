package com.openclassrooms.api.entity;

import jakarta.persistence.*;
import lombok.Data;

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

        private String created_at;

        private String updated_at;
}
