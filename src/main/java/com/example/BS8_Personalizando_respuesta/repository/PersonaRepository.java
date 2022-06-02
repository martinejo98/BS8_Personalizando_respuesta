package com.example.BS8_Personalizando_respuesta.repository;

import com.example.BS8_Personalizando_respuesta.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Integer> {
}
