package com.example.announcementservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class AnnouncementDto {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Departure point is required")
    private PointDto departPoint;

    @NotNull(message = "Arrival point is required")
    private PointDto arrivePoint;

    @URL(message = "Image must be a valid URL")
    private String image;

    @NotNull(message = "Zone ID is required")
    private Long zone_id;

    @NotNull(message = "Authorization ID is required")
    private Long authorization_id;

    @NotNull(message = "Organization ID is required")
    private Long organization_id;

    @NotNull(message = "Targets are required")
    @Size(min = 1, message = "There must be at least one target")
    private List<TargetDto> targetsDto;

}
