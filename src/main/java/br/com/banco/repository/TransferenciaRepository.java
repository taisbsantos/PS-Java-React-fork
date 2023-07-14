package br.com.banco.repository;

import br.com.banco.entitie.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findByContaId(Long contaId);
    List<Transferencia> findByContaIdAndDataTransferenciaGreaterThanEqualAndDataTransferenciaLessThanEqual(Long contaId, LocalDate dataInicio, LocalDate dataFim );
    List<Transferencia> findByContaIdAndNomeOperador(Long contaId, String nomeOperador);
    List<Transferencia> findByContaIdAndDataTransferenciaGreaterThanEqualAndDataTransferenciaLessThanEqualAndNomeOperador(Long contaId, LocalDate dataInicio, LocalDate dataFim, String nomeOperador );
}
