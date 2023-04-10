package com.clinic.controller;

import com.clinic.dtos.PatientDto;
import com.clinic.dtos.SpecialtyDto;
import com.clinic.service.HospitalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class HospitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HospitalService service;

    private SpecialtyDto allergology;
    private SpecialtyDto cardiology;

    private PatientDto patientDto;


    @BeforeEach
    void init(){

        patientDto = new PatientDto();
        patientDto.setName("Petter");
        patientDto.setLastName("Griffin");

        allergology = new SpecialtyDto();
                allergology.setId(1L);
                allergology.setName("alergologia");


        cardiology =  new SpecialtyDto();
                cardiology.setId(2L);
                cardiology.setName("cardiologia");


    }

    @Test
    void shouldCreateSpeciality() throws Exception {

        when(service.addSpecialty(any(SpecialtyDto.class))).thenReturn(allergology);

        this.mockMvc.perform(post("/specialties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(allergology)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",is(allergology.getName())));
    }

    @Test
    void shouldFetchAllSpecialties() throws Exception{

        List <SpecialtyDto> specialtyDtoList = new ArrayList<>();

        specialtyDtoList.add(allergology);
        specialtyDtoList.add(cardiology);

        when(service.findAllSpecialties()).thenReturn(specialtyDtoList);

        this.mockMvc.perform(get("/specialties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(specialtyDtoList.size())));
    }

    @Test
    void shouldFetchOneById() throws Exception {

        when(service.findSpecialty(anyLong())).thenReturn(allergology);

        this.mockMvc.perform(get("/specialties/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(allergology.getName())));
    }

    @Test
    void shouldDeletePatient() throws Exception {

        doNothing().when(service).deletePatient(anyLong());

        this.mockMvc.perform(delete("/patients/{id}",1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldFetchUpdatePatient() throws Exception{

        when(service.updatePatient(any(PatientDto.class))).thenReturn(patientDto);

        this.mockMvc.perform(put("/patients/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientDto)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(patientDto.getName())))
                .andExpect(jsonPath("$.lastName",is(patientDto.getLastName())));
    }



}
