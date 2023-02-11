package com.clinic.repositories;

import com.clinic.dtos.DoctorDto;
import com.clinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    List<Doctor> findAllBySpecialtyId (Long id);
}
