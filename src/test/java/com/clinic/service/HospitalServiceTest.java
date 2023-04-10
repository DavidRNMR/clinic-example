package com.clinic.service;


import com.clinic.dtos.DoctorDto;
import com.clinic.dtos.PathologyDto;
import com.clinic.dtos.PatientDto;
import com.clinic.dtos.SpecialtyDto;
import com.clinic.entity.*;
import com.clinic.exceptions.DoctorNotFoundException;
import com.clinic.exceptions.PathologyNotFoundException;
import com.clinic.mapper.HospitalMapper;
import com.clinic.repositories.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HospitalServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PathologyRepository pathologyRepository;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private EmergencyRepository emergencyRepository;

    @Mock
    private EmergencyManagerRepository emergencyManagerRepository;

    @Mock
    private HospitalMapper mapper;

    @InjectMocks
    private HospitalServiceImpl hospitalService;


    @Test
    @DisplayName("AddPatient")
    public void addPatientTest () throws DoctorNotFoundException, PathologyNotFoundException {
        Long doctorId = 1L;
        Long pathologyId = 2L;
        Long emergencyId = 3L;

        //definir comportamiento

        PatientDto patientDto = PatientDto.builder()
                .name("David")
                .lastName("Gomez")
                .build();

        Doctor doctor = Doctor.builder()
                .id(doctorId)
                .build();

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));

        Pathology pathology = Pathology.builder()
                .id(pathologyId)
                .build();

        when(pathologyRepository.findById(pathologyId)).thenReturn(Optional.of(pathology));

        Emergency emergency = Emergency.builder()
                .id(emergencyId)
                .build();

        when(emergencyRepository.findById(emergencyId)).thenReturn(Optional.of(emergency));

        when(patientRepository.save(any(Patient.class))).thenReturn(new Patient());


        PatientDto result = hospitalService.addPatient(patientDto,doctorId,pathologyId,emergencyId);

        //verificar

       verify(doctorRepository).findById(doctorId);
       verify(pathologyRepository).findById(pathologyId);
       verify(emergencyManagerRepository).findById(emergencyId);
       verify(patientRepository).save(any(Patient.class));

        assertNotNull(result);

    }



}
