package com.clinic.integration;

import com.clinic.entity.Patient;
import com.clinic.entity.Specialty;
import com.clinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HospitalIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    private Specialty specialty;
    private Specialty specialty1;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void beforeSetUp(){
        baseUrl = baseUrl + ":" + port + "/specialties";

        specialty = new Specialty();
        specialty.setName("allergology");
        specialtyRepository.save(specialty);

        specialty1 = new Specialty();
        specialty1.setName("cardiology");
        specialtyRepository.save(specialty1);


    }

    @AfterEach
    public void afterSetUp(){
        specialtyRepository.deleteAll();
    }

    @Test
    void shouldCreateSpecialtyTest(){

        Specialty specialtyFinal = new Specialty();

        specialtyFinal.setName("Neurology");

        Specialty specialtyNew = restTemplate.postForObject(baseUrl,specialtyFinal,Specialty.class);

        assertNotNull(specialtyNew);
        assertThat(specialtyNew.getId()).isNotNull();
    }

    @Test
    void shouldFetchSpecialtiesTest(){
        List<Specialty> specialties = restTemplate.getForObject(baseUrl, List.class);
        assertThat(specialties.size()).isEqualTo(2);
    }
}
