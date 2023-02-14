package com.clinic.repositories;

import com.clinic.entity.Pathology;
import com.clinic.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PathologyRepository extends JpaRepository<Pathology,Long> {

}
