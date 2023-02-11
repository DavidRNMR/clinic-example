package com.clinic.service;

import com.clinic.dtos.DoctorDto;
import com.clinic.dtos.PathologyDto;
import com.clinic.dtos.PatientDto;
import com.clinic.dtos.SpecialtyDto;
import com.clinic.entity.Doctor;
import com.clinic.entity.Pathology;
import com.clinic.entity.Patient;
import com.clinic.entity.Specialty;
import com.clinic.exceptions.DoctorNotFoundException;
import com.clinic.exceptions.PathologyNotFoundException;
import com.clinic.exceptions.PatientNotFoundException;
import com.clinic.exceptions.SpecialtyNotFoundException;
import com.clinic.mapper.HospitalMapper;
import com.clinic.repositories.DoctorRepository;
import com.clinic.repositories.PathologyRepository;
import com.clinic.repositories.PatientRepository;
import com.clinic.repositories.SpecialtyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
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


    @Override
    public List<PatientDto> findAllPatients() {

        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(patient -> mapper.fromPatient(patient)).collect(Collectors.toList());
    }

    @Override
    public PatientDto addPatient(PatientDto patientDto,Long doctorId, Long pathologyId) throws DoctorNotFoundException, PathologyNotFoundException {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new DoctorNotFoundException("Doctor not Found"));
        Pathology pathology = pathologyRepository.findById(pathologyId).orElseThrow(()-> new PathologyNotFoundException("Pathology not Found"));

        patientDto.setDoctorDto(mapper.fromDoctor(doctor));
        patientDto.setPathologyDto(mapper.fromPathology(pathology));

        Patient patient  = mapper.formPatientDto(patientDto);
        Patient saved = patientRepository.save(patient);

        return mapper.fromPatient(saved);
    }

    @Override
    public PatientDto findPatient(Long id) throws PatientNotFoundException {

        Patient patient = patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient not found"));
        return mapper.fromPatient(patient);
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto) {
        return null;
    }

    @Override
    public void deletePatient(Long id) {
        pathologyRepository.deleteById(id);
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

        SpecialtyDto specialtyDto = new SpecialtyDto();

        specialtyDto.setPathologyDtoList(pathologyDtoList);
        specialtyDto.setDoctorDtoList(doctorDtoList);
        specialtyDto.setId(specialty.getId());

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
    public DoctorDto addDoctor(DoctorDto doctorDto,Long specialtyId) throws SpecialtyNotFoundException {

        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow(()-> new SpecialtyNotFoundException("Specialty not found"));

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

        List<Patient> patients = patientRepository.findAllById(Collections.singleton(id));
        List<PatientDto> patientDtos = patients.stream().map(patient -> mapper.fromPatient(patient)).collect(Collectors.toList());

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setPatientDtoList(patientDtos);
        doctorDto.setId(doctor.getId());

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
    public PathologyDto findPathology(Long id) throws PathologyNotFoundException {

        Pathology pathology = pathologyRepository.findById(id).orElseThrow(()-> new PathologyNotFoundException("Pathology not found"));
        return mapper.fromPathology(pathology);
    }
}
