package com.openclassrooms.api.response;

import lombok.Data;

@Data
public class RentalsResponse {
    private Object rentals;
    public RentalsResponse(Object rentals) {
        this.rentals = rentals;
    }
}
