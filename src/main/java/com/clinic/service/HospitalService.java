package com.clinic.service;

import com.clinic.dtos.DoctorDto;
import com.clinic.dtos.PathologyDto;
import com.clinic.dtos.PatientDto;
import com.clinic.dtos.SpecialtyDto;
import com.clinic.exceptions.DoctorNotFoundException;
import com.clinic.exceptions.PathologyNotFoundException;
import com.clinic.exceptions.PatientNotFoundException;
import com.clinic.exceptions.SpecialtyNotFoundException;

import java.util.List;

public interface HospitalService {

    List<PatientDto> findAllPatients();
    PatientDto addPatient (PatientDto patientDto,Long doctorId, Long pathologyId) throws DoctorNotFoundException,PathologyNotFoundException;
    PatientDto findPatient(Long id) throws PatientNotFoundException;
    PatientDto updatePatient (PatientDto patientDto);
    void deletePatient (Long id);

    List<SpecialtyDto> findAllSpecialties();
    SpecialtyDto addSpecialty (SpecialtyDto specialtyDto);
    SpecialtyDto findSpecialty (Long id) throws SpecialtyNotFoundException;
    void deleteSpecialty (Long id);

    List<DoctorDto> findAllDoctors();
    DoctorDto addDoctor (DoctorDto doctorDto,Long specialtyId)throws SpecialtyNotFoundException;
    DoctorDto findDoctor (Long id) throws DoctorNotFoundException;

    List<PathologyDto> findAllPathologies();
    PathologyDto addPathology (PathologyDto pathologyDto,Long specialtyId) throws SpecialtyNotFoundException;
    PathologyDto findPathology (Long id) throws PathologyNotFoundException;
}
