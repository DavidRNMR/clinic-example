package com.clinic.dtos;


import lombok.Builder;
import lombok.Data;
import java.util.List;


@Data
@Builder
public class DoctorDto {

    private Long id;
    private String name;
    private List<PatientDto> patientDtoList;
    private SpecialtyDto specialtyDto;
    private EmergencyDto emergencyDto;
}
