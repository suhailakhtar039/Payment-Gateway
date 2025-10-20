package com.payment.gateway.dto;

import lombok.Data;

@Data
public class EnrollRequest {
    private Integer studentId;
    private Integer courseId;
}
