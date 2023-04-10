package com.clinic.service;

import com.clinic.dtos.*;
import com.clinic.exceptions.*;

import java.util.List;

public interface HospitalService {

    List<PatientDto> findAllPatients();
    PatientDto addPatient (PatientDto patientDto,Long doctorId, Long pathologyId, Long emergencyId) throws DoctorNotFoundException,PathologyNotFoundException;
    PatientDto findPatient(Long id) throws PatientNotFoundException,PathologyNotFoundException,DoctorNotFoundException;
    PatientDto updatePatient (PatientDto patientDto);
    void deletePatient (Long id);

    List<SpecialtyDto> findAllSpecialties();
    SpecialtyDto addSpecialty (SpecialtyDto specialtyDto);
    SpecialtyDto findSpecialty (Long id) throws SpecialtyNotFoundException;
    void deleteSpecialty (Long id);

    List<DoctorDto> findAllDoctors();
    DoctorDto addDoctor (DoctorDto doctorDto,Long specialtyId,Long emergencyId)throws SpecialtyNotFoundException;
    DoctorDto findDoctor (Long id) throws DoctorNotFoundException;

    List<PathologyDto> findAllPathologies();
    PathologyDto addPathology (PathologyDto pathologyDto,Long specialtyId) throws SpecialtyNotFoundException;
    PathologyDto findPathology (Long id) throws PathologyNotFoundException, SpecialtyNotFoundException;
    EmergencyDto addEmergency (EmergencyDto emergencyDto,Long managerId) throws EmergencyManagerNotFoundException;
    EmergencyManagerDto addManager (EmergencyManagerDto emergencyManagerDto);
    EmergencyManagerDto findOneManager (Long id) throws EmergencyManagerNotFoundException;
    EmergencyDto findOneEmergency (Long id) throws EmergencyNotFoundException;

}
