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
@Table(name="speciaties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "specialty",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Doctor> doctorList;

    @OneToMany(mappedBy = "specialty",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pathology> pathologyList;


}
