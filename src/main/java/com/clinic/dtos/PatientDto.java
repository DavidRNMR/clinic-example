package com.clinic.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class PatientDto implements Serializable {

    private Long id;
    private String name;
    private String lastName;
    private DoctorDto doctorDto;
    private PathologyDto pathologyDto;
}
