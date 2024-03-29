package com.clinic.mapper;

import com.clinic.dtos.*;
import com.clinic.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class HospitalMapper {

    public PatientDto fromPatient (Patient patient){

        PatientDto patientDto =  new PatientDto();
        BeanUtils.copyProperties(patient,patientDto);

        patientDto.setDoctorDto(fromDoctor(patient.getDoctor()));
        patientDto.setPathologyDto(fromPathology(patient.getPathology()));
        patientDto.setEmergencyDto(fromEmergency(patient.getEmergency()));
        return patientDto;
    }

    public Patient formPatientDto (PatientDto patientDto){

        Patient patient =  new Patient();
        BeanUtils.copyProperties(patientDto,patient);

        patient.setDoctor(fromDoctorDto(patientDto.getDoctorDto()));
        patient.setPathology(fromPathologyDto(patientDto.getPathologyDto()));
        patient.setEmergency(fromEmergencyDto(patientDto.getEmergencyDto()));
        return patient;
    }

    public DoctorDto fromDoctor(Doctor doctor){

        DoctorDto doctorDto =  new DoctorDto();
        BeanUtils.copyProperties(doctor,doctorDto);

        doctorDto.setSpecialtyDto(fromSpecialty(doctor.getSpecialty()));
        doctorDto.setEmergencyDto(fromEmergency(doctor.getEmergency()));
        return doctorDto;
    }

    public Doctor fromDoctorDto (DoctorDto doctorDto){

        Doctor doctor =  new Doctor();
        BeanUtils.copyProperties(doctorDto,doctor);

        doctor.setSpecialty(fromSpecialtyDto(doctorDto.getSpecialtyDto()));
        doctor.setEmergency(fromEmergencyDto(doctorDto.getEmergencyDto()));
        return doctor;
    }

    public PathologyDto fromPathology (Pathology pathology){

        PathologyDto pathologyDto = new PathologyDto();
        BeanUtils.copyProperties(pathology,pathologyDto);

        pathologyDto.setSpecialtyDto(fromSpecialty(pathology.getSpecialty()));
        return pathologyDto;
    }

    public Pathology fromPathologyDto (PathologyDto pathologyDto){

        Pathology pathology = new Pathology();
        BeanUtils.copyProperties(pathologyDto,pathology);

        pathology.setSpecialty(fromSpecialtyDto(pathologyDto.getSpecialtyDto()));
        return pathology;
    }

    public SpecialtyDto fromSpecialty (Specialty specialty){

        SpecialtyDto specialtyDto = new SpecialtyDto();
        BeanUtils.copyProperties(specialty,specialtyDto);
        return specialtyDto;
    }

    public Specialty fromSpecialtyDto (SpecialtyDto specialtyDto){

        Specialty specialty = new Specialty();
        BeanUtils.copyProperties(specialtyDto,specialty);
        return specialty;
    }

    public EmergencyDto fromEmergency (Emergency emergency){

        EmergencyDto emergencyDto = new EmergencyDto();
        BeanUtils.copyProperties(emergency,emergencyDto);

        emergencyDto.setEmergencyManagerDto(fromEmergencyManager(emergency.getManager()));
        return emergencyDto;

    }

    public Emergency fromEmergencyDto (EmergencyDto emergencyDto){

        Emergency emergency =  new Emergency();
        BeanUtils.copyProperties(emergencyDto,emergency);

        emergency.setManager(fromEmergencyManagerDto(emergencyDto.getEmergencyManagerDto()));
        return emergency;
    }

    public EmergencyManagerDto fromEmergencyManager (EmergencyManager emergencyManager){

        EmergencyManagerDto emergencyManagerDto =  new EmergencyManagerDto();
        BeanUtils.copyProperties(emergencyManager,emergencyManagerDto);

        return emergencyManagerDto;
    }

    public EmergencyManager fromEmergencyManagerDto (EmergencyManagerDto emergencyManagerDto){

        EmergencyManager emergencyManager = new EmergencyManager();
        BeanUtils.copyProperties(emergencyManagerDto,emergencyManager);

        return emergencyManager;
    }


}
