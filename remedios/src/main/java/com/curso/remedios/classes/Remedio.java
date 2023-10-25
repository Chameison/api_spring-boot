package com.curso.remedios.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "remedios")
@Table(name = "Remedio")

public class Remedio {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) //Significa que 
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Via via;
    private String lote;
    private String quantidade;
    private String validade;

     @Enumerated(EnumType.STRING)
    private Laboratorio laboratorio;
}
