package com.clinic.repositories;

import com.clinic.entity.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathologyRepository extends JpaRepository<Pathology,Long> {
}
