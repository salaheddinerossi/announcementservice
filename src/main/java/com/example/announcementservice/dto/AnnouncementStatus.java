package com.example.announcementservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnnouncementStatus {

    @NotNull(message = "id cannot be null")
    private Long id;

    @NotBlank(message = "status is required")
    private String status;

}
