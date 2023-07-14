package br.com.banco.service;

import br.com.banco.dto.DadosEntradaDTO;
import br.com.banco.dto.ExtratoDTO;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.entitie.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {

    private final TransferenciaRepository transferenciaRepository;

    public ContaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public ExtratoDTO gerarExtrato(DadosEntradaDTO dadosEntradaDTO){
        List<Transferencia> transferencias = null;
        List<Transferencia> transferenciasTotais = null;
        BigDecimal saldoTotal = BigDecimal.ZERO;
        BigDecimal saldoPeriodo = BigDecimal.ZERO;

        transferenciasTotais = getTransferenciasByContaId(dadosEntradaDTO.getNumeroConta());
        saldoTotal = calcularSaldo(transferenciasTotais);

        if (dadosEntradaDTO.getDataInicio() != null && dadosEntradaDTO.getDataFim() != null && dadosEntradaDTO.getNomeOperador() != null) {
            transferencias = getTransferenciasByContaIdAndDataTransferenciaAndNomeOperador(dadosEntradaDTO);
            saldoPeriodo = calcularSaldo(transferencias);
        } else if(dadosEntradaDTO.getNomeOperador() != null && (dadosEntradaDTO.getDataInicio() == null ||  dadosEntradaDTO.getDataFim() == null) ){
            transferencias = getTransferenciasByContaIdAndNomeOperador(dadosEntradaDTO);
            saldoPeriodo = saldoTotal;
        }else if(dadosEntradaDTO.getDataInicio() != null && dadosEntradaDTO.getDataFim() != null){
            transferencias = getTransferenciasByContaIdAndDataTransferencia(dadosEntradaDTO);
            saldoPeriodo = calcularSaldo(transferencias);
        }else{
            transferencias = getTransferenciasByContaId(dadosEntradaDTO.getNumeroConta());
            saldoPeriodo = saldoTotal;
        }

        List<TransferenciaDTO> transferenciasDTO = transferencias.stream()
                .map(this::convertToTransferenciaDTO)
                .collect(Collectors.toList());

        ExtratoDTO extratoDTO= new ExtratoDTO(dadosEntradaDTO.getNumeroConta(), dadosEntradaDTO.getDataInicio(), dadosEntradaDTO.getDataFim(), transferenciasDTO, saldoTotal, saldoPeriodo);

        return extratoDTO;
    }

    public List<Transferencia> getTransferenciasByContaId(Long contaId) {
        return transferenciaRepository.findByContaId(contaId);
    }

    public List<Transferencia> getTransferenciasByContaIdAndDataTransferencia(DadosEntradaDTO dadosEntradaDTO) {

        return transferenciaRepository.findByContaIdAndDataTransferenciaGreaterThanEqualAndDataTransferenciaLessThanEqual(dadosEntradaDTO.getNumeroConta(), dadosEntradaDTO.getDataInicio(), dadosEntradaDTO.getDataFim());
    }

    public List<Transferencia> getTransferenciasByContaIdAndNomeOperador(DadosEntradaDTO dadosEntradaDTO) {
        return transferenciaRepository.findByContaIdAndNomeOperador(dadosEntradaDTO.getNumeroConta(), dadosEntradaDTO.getNomeOperador());
    }

    public List<Transferencia> getTransferenciasByContaIdAndDataTransferenciaAndNomeOperador(DadosEntradaDTO dadosEntradaDTO) {

        return transferenciaRepository.findByContaIdAndDataTransferenciaGreaterThanEqualAndDataTransferenciaLessThanEqualAndNomeOperador(dadosEntradaDTO.getNumeroConta(),
                dadosEntradaDTO.getDataInicio(), dadosEntradaDTO.getDataFim(), dadosEntradaDTO.getNomeOperador());
    }

    private TransferenciaDTO convertToTransferenciaDTO(Transferencia transferencia) {
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setDataTransferencia(transferencia.getDataTransferencia());
        transferenciaDTO.setValor(transferencia.getValor());
        transferenciaDTO.setTipo(transferencia.getTipo());
        transferenciaDTO.setNomeOperadorTransacao(transferencia.getNomeOperador());
        return transferenciaDTO;
    }

    public BigDecimal calcularSaldo(List<Transferencia> transferencias){

        List<TransferenciaDTO> transferenciasDTO = transferencias.stream()
                .map(this::convertToTransferenciaDTO)
                .collect(Collectors.toList());

        BigDecimal saldoTotal = transferenciasDTO.stream()
                .map(TransferenciaDTO::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return saldoTotal;
    }
}



