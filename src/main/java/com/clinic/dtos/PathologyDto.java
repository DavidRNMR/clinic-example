package com.clinic.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PathologyDto implements Serializable {

    private Long id;
    private String name;
    private List<PatientDto> patientDtoList;
    private SpecialtyDto specialtyDto;
}
