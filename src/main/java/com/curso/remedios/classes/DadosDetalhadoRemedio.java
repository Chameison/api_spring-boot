package com.curso.remedios.classes;

import java.time.LocalDate;

public record DadosDetalhadoRemedio(

        Long id,
        String nome,
        Via via,
        String lote,
        int quantidade,
        LocalDate validade,

        Laboratorio laboratorio,
        Boolean ativo) {
    public DadosDetalhadoRemedio(Remedio remedio) {
        this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getQuantidade(),
                remedio.getValidade(), remedio.getLaboratorio(), remedio.isAtivo());
    }

}
