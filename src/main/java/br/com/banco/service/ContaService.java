package br.com.banco.service;

import br.com.banco.dto.DadosEntradaDTO;
import br.com.banco.dto.ExtratoDTO;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.entitie.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;

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

        if (dadosEntradaDTO.getDataInicio() != null && dadosEntradaDTO.getDataFim() != null && dadosEntradaDTO.getNomeOperador() != null) {
            transferencias = getTransferenciasByContaIdAndDataTransferenciaAndNomeOperador(dadosEntradaDTO);
        } else if(dadosEntradaDTO.getNomeOperador() != null && (dadosEntradaDTO.getDataInicio() == null ||  dadosEntradaDTO.getDataFim() == null) ){
            transferencias = getTransferenciasByContaIdAndNomeOperador(dadosEntradaDTO);
        }else if(dadosEntradaDTO.getDataInicio() != null && dadosEntradaDTO.getDataFim() != null){
            transferencias = getTransferenciasByContaIdAndDataTransferencia(dadosEntradaDTO);
        }else{
            transferencias = getTransferenciasByContaId(dadosEntradaDTO.getNumeroConta());
        }

        List<TransferenciaDTO> transferenciasDTO = transferencias.stream()
                .map(this::convertToTransferenciaDTO)
                .collect(Collectors.toList());

        ExtratoDTO extratoDTO= new ExtratoDTO(dadosEntradaDTO.getNumeroConta(), dadosEntradaDTO.getDataInicio(), dadosEntradaDTO.getDataFim(), transferenciasDTO);

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
}

