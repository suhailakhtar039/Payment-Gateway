package com.payment.gateway.dto;

import lombok.Data;

@Data
public class CreateCourseRequest {
    private String courseName;
    private Integer courseRating;
}
