package com.clinic.dtos;

import com.clinic.entity.Specialty;
import lombok.Data;

import java.util.List;

@Data
public class DoctorDto {

    private Long id;
    private String name;
    private List<PatientDto> patientDtoList;
    private SpecialtyDto specialtyDto;
}
