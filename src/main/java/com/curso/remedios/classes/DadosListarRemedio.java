package com.curso.remedios.classes;

import java.time.LocalDate;
//nosso dto
public record DadosListarRemedio(Long id,  String nome, Via via, String lote, Laboratorio laboratorio, LocalDate validade) { //decidimos o que quisermos mostrar

    public DadosListarRemedio(Remedio remedio) {
        this(remedio.getId(),remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(), remedio.getValidade());
    }
}
