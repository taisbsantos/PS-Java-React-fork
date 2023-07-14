package br.com.banco.entitie;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_transferencia", nullable = false)
    private LocalDate dataTransferencia;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    public Long getId() {
        return id;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNomeOperador() {
        return nomeOperador;
    }

    public Conta getConta() {
        return conta;
    }
}