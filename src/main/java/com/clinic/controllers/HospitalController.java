package com.clinic.controllers;

import com.clinic.dtos.*;
import com.clinic.exceptions.*;
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

    @PostMapping("/doctors/{id}/{emergencyId}")
    public DoctorDto addDoctor (@RequestBody DoctorDto doctorDto,
                                @PathVariable Long id,
                                @PathVariable Long emergencyId) throws SpecialtyNotFoundException {
        return  service.addDoctor(doctorDto,id,emergencyId);
    }

    @PostMapping("/patients/{doctorId}/{pathologyId}/{emergencyId}")
    public PatientDto addPatient (@RequestBody PatientDto patientDto,
                                  @PathVariable Long doctorId,
                                  @PathVariable Long pathologyId,
                                  @PathVariable(required = false) Long emergencyId) throws DoctorNotFoundException, PathologyNotFoundException {

        return service.addPatient(patientDto,doctorId,pathologyId,emergencyId);
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
    public PathologyDto findOnePathology (@PathVariable Long id) throws PathologyNotFoundException, SpecialtyNotFoundException {
        return service.findPathology(id);
    }

    @GetMapping("/patients/{id}")
    public PatientDto findOnePatient (@PathVariable Long id) throws PatientNotFoundException, DoctorNotFoundException, PathologyNotFoundException {
        return service.findPatient(id);
    }

    @PostMapping("/emergencies/{managerId}")
    public EmergencyDto addEmergency (@RequestBody EmergencyDto emergencyDto, @PathVariable Long managerId) throws EmergencyManagerNotFoundException{
        return service.addEmergency(emergencyDto,managerId);
    }

    @PostMapping("/managers")
    public EmergencyManagerDto addManager (@RequestBody EmergencyManagerDto emergencyManagerDto){
        return service.addManager(emergencyManagerDto);
    }

    @GetMapping("/emergencies/{id}")
    public EmergencyDto findOneEmergency (@PathVariable Long id) throws EmergencyNotFoundException{
        return service.findOneEmergency(id);
    }

    @GetMapping("/managers/{id}")
    public EmergencyManagerDto findOneManger (@PathVariable Long id) throws EmergencyManagerNotFoundException{
        return service.findOneManager(id);
    }


}
