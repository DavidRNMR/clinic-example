package com.clinic.mapper;

import com.clinic.dtos.DoctorDto;
import com.clinic.dtos.PathologyDto;
import com.clinic.dtos.PatientDto;
import com.clinic.dtos.SpecialtyDto;
import com.clinic.entity.Doctor;
import com.clinic.entity.Pathology;
import com.clinic.entity.Patient;
import com.clinic.entity.Specialty;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class HospitalMapper {

    public PatientDto fromPatient (Patient patient){

        PatientDto patientDto = new PatientDto();
        BeanUtils.copyProperties(patient,patientDto);

        patientDto.setDoctorDto(fromDoctor(patient.getDoctor()));
        patientDto.setPathologyDto(fromPathology(patient.getPathology()));
        return patientDto;
    }

    public Patient formPatientDto (PatientDto patientDto){

        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDto,patient);

        patient.setDoctor(fromDoctorDto(patientDto.getDoctorDto()));
        patient.setPathology(fromPathologyDto(patientDto.getPathologyDto()));
        return patient;
    }

    public DoctorDto fromDoctor(Doctor doctor){

        DoctorDto doctorDto = new DoctorDto();
        BeanUtils.copyProperties(doctor,doctorDto);

        doctorDto.setSpecialtyDto(fromSpecialty(doctor.getSpecialty()));
        return doctorDto;
    }

    public Doctor fromDoctorDto (DoctorDto doctorDto){

        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDto,doctor);

        doctor.setSpecialty(fromSpecialtyDto(doctorDto.getSpecialtyDto()));
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
}
