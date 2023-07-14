package br.com.banco.entitie;

import javax.persistence.*;

@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long id;

    @Column(name = "nome_responsavel", nullable = false)
    private String nomeOperador;

    public Long getId() {
        return id;
    }

    public String getNomeOperador() {
        return nomeOperador;
    }
}