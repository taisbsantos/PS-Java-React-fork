package br.com.banco.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferenciaDTO {
    private LocalDate dataTransferencia;
    private BigDecimal valor;
    private String tipo;
    private String nomeOperadorTransacao;

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNomeOperadorTransacao() {
        return nomeOperadorTransacao;
    }

    public void setNomeOperadorTransacao(String nomeOperadorTransacao) {
        this.nomeOperadorTransacao = nomeOperadorTransacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
