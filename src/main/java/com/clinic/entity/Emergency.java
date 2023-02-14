package com.clinic.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="emergencies")
public class Emergency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="emergencies_id",referencedColumnName = "id")
    private EmergencyManager manager;

    @OneToMany(mappedBy = "emergency",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Patient> patientList;

    @OneToMany(mappedBy = "emergency",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Doctor> doctorList;

}
