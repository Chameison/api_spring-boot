package com.curso.remedios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.remedios.classes.Remedio;

public interface RemedioRepository extends JpaRepository<Remedio, Long >{

    List<Remedio> findAllByAtivoTrue();
    
}
