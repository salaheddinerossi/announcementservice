package com.example.announcementservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PointDto {

    @NotNull(message = "Latitude is required")
    private double latitude;

    @NotNull(message = "Longitude is required")
    private double longitude;

}
