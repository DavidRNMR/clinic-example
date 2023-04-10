package com.clinic.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmergencyManagerDto {

    private Long id;
    private String name;
    private String lastName;
}
