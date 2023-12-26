package com.example.announcementservice.dto;

import lombok.Data;

@Data
public class TargetDto {

    private Long id;

    private String name;

    private Long currentValue;

    private Long targetValue;

}