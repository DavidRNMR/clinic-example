package com.clinic.service;

import com.clinic.dtos.*;
import com.clinic.entity.*;
import com.clinic.exceptions.*;
import com.clinic.mapper.HospitalMapper;
import com.clinic.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class HospitalServiceImpl implements HospitalService{

    private PatientRepository patientRepository;
    private PathologyRepository pathologyRepository;
    private DoctorRepository doctorRepository;
    private SpecialtyRepository specialtyRepository;
    private HospitalMapper mapper;
    private EmergencyManagerRepository emergencyManagerRepository;
    private EmergencyRepository emergencyRepository;


    @Override
    public List<PatientDto> findAllPatients() {

        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(patient -> mapper.fromPatient(patient)).collect(Collectors.toList());
    }

    @Override
    public PatientDto addPatient(PatientDto patientDto,Long doctorId, Long pathologyId,Long emergencyId) throws DoctorNotFoundException,PathologyNotFoundException{

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new DoctorNotFoundException("Doctor not Found"));
        Pathology pathology = pathologyRepository.findById(pathologyId).orElseThrow(()-> new PathologyNotFoundException("Pathology not Found"));
        Optional<Emergency> emergency = emergencyRepository.findById(emergencyId);

        if(emergency.isPresent()){

            Emergency emergency1 = emergency.get();
            patientDto.setEmergencyDto(mapper.fromEmergency(emergency1));
        }

        patientDto.setDoctorDto(mapper.fromDoctor(doctor));
        patientDto.setPathologyDto(mapper.fromPathology(pathology));

        Patient patient  = mapper.formPatientDto(patientDto);
        Patient saved = patientRepository.save(patient);

        return mapper.fromPatient(saved);
    }

    @Override
    public PatientDto findPatient(Long id) throws PatientNotFoundException,DoctorNotFoundException,PathologyNotFoundException {

        Patient patient = patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient not found"));
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new DoctorNotFoundException("Doctor not found"));
        Pathology pathology = pathologyRepository.findById(id).orElseThrow(()-> new PathologyNotFoundException("Pathology not found"));
        Optional<Emergency> emergency = emergencyRepository.findById(id);

        PatientDto patientDto = mapper.fromPatient(patient);
        patientDto.setPathologyDto(mapper.fromPathology(pathology));
        patientDto.setDoctorDto(mapper.fromDoctor(doctor));

        if(emergency.isPresent()){
            Emergency emergency1 = emergency.get();
            patientDto.setEmergencyDto(mapper.fromEmergency(emergency1));
        }
        return patientDto;
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto) {
        return null;
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<SpecialtyDto> findAllSpecialties() {

        List<Specialty> specialties = specialtyRepository.findAll();

        return specialties.stream().map(specialty -> mapper.fromSpecialty(specialty)).collect(Collectors.toList());
    }

    @Override
    public SpecialtyDto addSpecialty(SpecialtyDto specialtyDto)  {

        List<Doctor> doctors = doctorRepository.findAll();
        List<Pathology> pathologies = pathologyRepository.findAll();


        specialtyDto.setDoctorDtoList(doctors.stream()
                .map(doctor -> mapper.fromDoctor(doctor)).collect(Collectors.toList()));

        specialtyDto.setPathologyDtoList(pathologies.stream()
                .map(pathology -> mapper.fromPathology(pathology)).collect(Collectors.toList()));


        Specialty specialty = mapper.fromSpecialtyDto(specialtyDto);
        Specialty saved = specialtyRepository.save(specialty);

        return mapper.fromSpecialty(saved);
    }

    @Override
    public SpecialtyDto findSpecialty(Long id) throws SpecialtyNotFoundException {

        Specialty specialty = specialtyRepository.findById(id).orElseThrow(()-> new SpecialtyNotFoundException("Specialty not found"));

        List<Doctor> doctors = doctorRepository.findAllById(Collections.singleton(id));
        List<Pathology> pathologies = pathologyRepository.findAllById(Collections.singleton(id));

        List<DoctorDto> doctorDtoList = doctors.stream().map(doctor -> mapper.fromDoctor(doctor)).collect(Collectors.toList());
        List<PathologyDto> pathologyDtoList = pathologies.stream().map(pathology -> mapper.fromPathology(pathology)).collect(Collectors.toList());

        SpecialtyDto specialtyDto = mapper.fromSpecialty(specialty);
        specialtyDto.setPathologyDtoList(pathologyDtoList);
        specialtyDto.setDoctorDtoList(doctorDtoList);

        return specialtyDto;
    }

    @Override
    public void deleteSpecialty(Long id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public List<DoctorDto> findAllDoctors() {

        List<Doctor> doctors = doctorRepository.findAll();

        return doctors.stream().map(doctor -> mapper.fromDoctor(doctor)).collect(Collectors.toList());
    }

    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto,Long specialtyId,Long emergencyId) throws SpecialtyNotFoundException {

        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow(()-> new SpecialtyNotFoundException("Specialty not found"));
        Optional<Emergency> emergency = emergencyRepository.findById(emergencyId);

        if(emergency.isPresent()){

            Emergency emergency1 = emergency.get();
            doctorDto.setEmergencyDto(mapper.fromEmergency(emergency1));
        }

        List<Patient> patients = patientRepository.findAll();


        doctorDto.setPatientDtoList(patients.stream()
                .map(patient -> mapper.fromPatient(patient)).collect(Collectors.toList()));

        doctorDto.setSpecialtyDto(mapper.fromSpecialty(specialty));

        Doctor doctor = mapper.fromDoctorDto(doctorDto);
        Doctor saved = doctorRepository.save(doctor);

        return mapper.fromDoctor(saved);
    }

    @Override
    public DoctorDto findDoctor(Long id) throws DoctorNotFoundException {

        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new DoctorNotFoundException("Doctor not found"));
        Optional<Emergency> emergency = emergencyRepository.findById(id);

        if(emergency.isPresent()){
            Emergency emergency1 = emergency.get();
            doctor.setEmergency(emergency1);
        }

        List<Patient> patients = patientRepository.findAllById(Collections.singleton(id));
        List<PatientDto> patientDtos = patients.stream().map(patient -> mapper.fromPatient(patient)).collect(Collectors.toList());

        DoctorDto doctorDto = mapper.fromDoctor(doctor);
        doctorDto.setPatientDtoList(patientDtos);

        return doctorDto;
    }

    @Override
    public List<PathologyDto> findAllPathologies() {

        List<Pathology> pathologies = pathologyRepository.findAll();

        return pathologies.stream().map(pathology -> mapper.fromPathology(pathology)).collect(Collectors.toList());
    }

    @Override
    public PathologyDto addPathology(PathologyDto pathologyDto, Long specialtyId) throws SpecialtyNotFoundException{

        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow(()->new SpecialtyNotFoundException("Specialty not found"));

        List<Patient> patients = patientRepository.findAll();
        List<PatientDto> patientDtoList = patients.stream().map(patient -> mapper.fromPatient(patient)).toList();

        pathologyDto.setPatientDtoList(patientDtoList);
        pathologyDto.setSpecialtyDto(mapper.fromSpecialty(specialty));

        Pathology pathology = mapper.fromPathologyDto(pathologyDto);
        Pathology saved = pathologyRepository.save(pathology);

        return mapper.fromPathology(saved);
    }

    @Override
    public PathologyDto findPathology(Long id) throws PathologyNotFoundException,SpecialtyNotFoundException{

        Specialty specialty = specialtyRepository.findById(id).orElseThrow(()-> new SpecialtyNotFoundException("Specialty not found"));
        Pathology pathology = pathologyRepository.findById(id).orElseThrow(()-> new PathologyNotFoundException("Pathology not found"));

        List<Patient> patients = patientRepository.findAll();
        List<PatientDto> patientDtoList = patients.stream().map(patient -> mapper.fromPatient(patient)).collect(Collectors.toList());

        PathologyDto pathologyDto = mapper.fromPathology(pathology);
        pathologyDto.setSpecialtyDto(mapper.fromSpecialty(specialty));
        pathologyDto.setPatientDtoList(patientDtoList);

        return pathologyDto;
    }

    @Override
    public EmergencyDto addEmergency(EmergencyDto emergencyDto, Long managerId) throws EmergencyManagerNotFoundException {

        EmergencyManager emergencyManager = emergencyManagerRepository.findById(managerId).orElseThrow(()-> new EmergencyManagerNotFoundException("Manager not found"));

        emergencyDto.setEmergencyManagerDto(mapper.fromEmergencyManager(emergencyManager));

        Emergency emergency = mapper.fromEmergencyDto(emergencyDto);
        Emergency saved = emergencyRepository.save(emergency);

        return mapper.fromEmergency(saved);
    }

    @Override
    public EmergencyManagerDto addManager(EmergencyManagerDto emergencyManagerDto) {

        EmergencyManager emergencyManager = mapper.fromEmergencyManagerDto(emergencyManagerDto);
        EmergencyManager saved = emergencyManagerRepository.save(emergencyManager);

        return mapper.fromEmergencyManager(saved);
    }

    @Override
    public EmergencyManagerDto findOneManager(Long id) throws EmergencyManagerNotFoundException {

        EmergencyManager emergencyManager = emergencyManagerRepository.findById(id).orElseThrow(()-> new EmergencyManagerNotFoundException("Manager not found"));
        return mapper.fromEmergencyManager(emergencyManager);
    }

    @Override
    public EmergencyDto findOneEmergency(Long id) throws EmergencyNotFoundException {

        Emergency emergency = emergencyRepository.findById(id).orElseThrow(()-> new EmergencyNotFoundException("Emergency not found"));
        return mapper.fromEmergency(emergency);
    }

}
