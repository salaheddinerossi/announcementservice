package com.example.announcementservice.response;

import lombok.Data;

@Data
public class TargetResponse {

    private Long id;

    private String name;

    private Long currentValue;

    private Long targetValue;

}