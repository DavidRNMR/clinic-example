package com.clinic.repositories;

import com.clinic.entity.EmergencyManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyManagerRepository extends JpaRepository<EmergencyManager,Long> {
}
