package com.curso.remedios.classes;

import java.time.LocalDate;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastrosRemedio(

//aplicando validaçÕes
        @NotBlank
        String nome, 
        @Enumerated
        Via via, 
        @NotBlank
        String lote, 
        
        int quantidade, 
        
        @Future //definimos que a validade deve ser do fuuturo
        LocalDate validade,
        @Enumerated
        Laboratorio laboratorio) {

}
