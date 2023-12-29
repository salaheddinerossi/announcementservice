package com.example.announcementservice.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class TargetDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Current value is required")
    @Positive(message = "Current value must be positive")
    private Long currentValue;

    @NotNull(message = "Target value is required")
    @Positive(message = "Target value must be positive")
    private Long targetValue;

}
