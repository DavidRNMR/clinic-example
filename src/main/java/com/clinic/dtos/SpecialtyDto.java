package com.clinic.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SpecialtyDto implements Serializable {

    private Long id;
    private String name;
    private List<DoctorDto> doctorDtoList;
    private List<PathologyDto> pathologyDtoList;
}
