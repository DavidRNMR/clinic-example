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
@Table(name="speciaties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "specialty",fetch = FetchType.LAZY)
    private List<Doctor> doctorList;

    @OneToMany(mappedBy = "specialty",fetch = FetchType.LAZY)
    private List<Pathology> pathologyList;


}
