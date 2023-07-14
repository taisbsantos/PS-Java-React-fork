package br.com.banco.dto;

import java.time.LocalDate;

public class DadosEntradaDTO {
    Long numeroConta;
    LocalDate dataInicio;
    LocalDate dataFim;
    String nomeOperador;

    public DadosEntradaDTO(Long numeroConta, LocalDate dataInicio, LocalDate dataFim, String nomeOperador) {
        this.numeroConta = numeroConta;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.nomeOperador = nomeOperador;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public String getNomeOperador() {
        return nomeOperador;
    }
}
