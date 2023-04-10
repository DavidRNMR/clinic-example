package com.clinic.dtos;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class EmergencyDto implements Serializable {

    private Long id;
    private EmergencyManagerDto emergencyManagerDto;
    private List<DoctorDto> doctorDtoList;
    private List<PatientDto> patientDtoList;
}
