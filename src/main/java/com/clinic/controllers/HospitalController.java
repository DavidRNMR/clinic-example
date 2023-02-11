package com.clinic.controllers;

import com.clinic.dtos.DoctorDto;
import com.clinic.dtos.PathologyDto;
import com.clinic.dtos.PatientDto;
import com.clinic.dtos.SpecialtyDto;
import com.clinic.exceptions.DoctorNotFoundException;
import com.clinic.exceptions.PathologyNotFoundException;
import com.clinic.exceptions.PatientNotFoundException;
import com.clinic.exceptions.SpecialtyNotFoundException;
import com.clinic.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class HospitalController {

    private HospitalService service;




    @PostMapping("/specialties")
    public SpecialtyDto addSpecialty (@RequestBody SpecialtyDto specialtyDto){
        return service.addSpecialty(specialtyDto);
    }

    @PostMapping("/pathologies/{id}")
    public PathologyDto addPathology (@RequestBody PathologyDto pathologyDto, @PathVariable Long id) throws SpecialtyNotFoundException {
        return service.addPathology(pathologyDto,id);
    }

    @PostMapping("/doctors/{id}")
    public DoctorDto addDoctor (@RequestBody DoctorDto doctorDto, @PathVariable Long id) throws SpecialtyNotFoundException {
        return  service.addDoctor(doctorDto,id);
    }

    @PostMapping("/patients/{doctorId}/{pathologyId}")
    public PatientDto addPatient (@RequestBody PatientDto patientDto,
                                  @PathVariable Long doctorId,
                                  @PathVariable Long pathologyId) throws DoctorNotFoundException, PathologyNotFoundException {

        return service.addPatient(patientDto,doctorId,pathologyId);
    }

    @GetMapping("/doctors")
    public List<DoctorDto> findDoctors (){
        return service.findAllDoctors();
    }

    @GetMapping("/patients")
    public List<PatientDto> findPatients (){
        return service.findAllPatients();
    }

    @GetMapping("/pathologies")
    public List<PathologyDto> findPathologies (){
        return  service.findAllPathologies();
    }

    @GetMapping("/specialties")
    public List<SpecialtyDto> findSpecialties (){
        return service.findAllSpecialties();
    }

    @GetMapping("/specialties/{id}")
    public SpecialtyDto findOneSpecialty (@PathVariable Long id) throws SpecialtyNotFoundException{
        return service.findSpecialty(id);
    }

    @GetMapping("/doctors/{id}")
    public DoctorDto findOnedoctor (@PathVariable Long id) throws DoctorNotFoundException{
        return service.findDoctor(id);
    }

    @GetMapping("/pathologies/{id}")
    public PathologyDto findOnePathology (@PathVariable Long id) throws PathologyNotFoundException{
        return service.findPathology(id);
    }

    @GetMapping("/patients/{id}")
    public PatientDto findOnePatient (@PathVariable Long id) throws PatientNotFoundException {
        return service.findPatient(id);
    }

}
