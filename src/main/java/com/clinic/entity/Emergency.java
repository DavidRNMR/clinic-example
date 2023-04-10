package com.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="emergencies")
public class Emergency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="manager_id",referencedColumnName = "id")
    private EmergencyManager manager;

    @OneToMany(mappedBy = "emergency",fetch = FetchType.LAZY)
    private List<Patient> patientList;

    @OneToMany(mappedBy = "emergency",fetch = FetchType.LAZY)
    private List<Doctor> doctorList;

}
