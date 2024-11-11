package ru.t1.java.demo.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetricDto {
    private long executionTime;
    private String methodName;
    private String params;
}
