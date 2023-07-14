package br.com.banco.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ExtratoDTO {

    Long numeroConta;
    LocalDate dataInicio;
    LocalDate dataFim;
    BigDecimal saldoTotal;
    BigDecimal saldoPeriodo;
    List<TransferenciaDTO> transferencias;

    public ExtratoDTO(Long numeroConta, LocalDate dataInicio, LocalDate dataFim, List<TransferenciaDTO> transferencias,
                      BigDecimal saldoTotal, BigDecimal saldoPeriodo) {
        this.numeroConta = numeroConta;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.transferencias = transferencias;
        this.saldoTotal = saldoTotal;
        this.saldoPeriodo = saldoPeriodo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }


    public List<TransferenciaDTO> getTransferencias() {
        return transferencias;
    }

    public void setTransferencias(List<TransferenciaDTO> transferencias) {
        this.transferencias = transferencias;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(BigDecimal saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public BigDecimal getSaldoPeriodo() {
        return saldoPeriodo;
    }

    public void setSaldoPeriodo(BigDecimal saldoPeriodo) {
        this.saldoPeriodo = saldoPeriodo;
    }
}
