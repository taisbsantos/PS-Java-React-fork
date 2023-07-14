package br.com.banco.controller;

import br.com.banco.dto.DadosEntradaDTO;
import br.com.banco.dto.ExtratoDTO;
import br.com.banco.service.ContaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping("/extrato/{numeroConta}")
    public ExtratoDTO getAccountTransactions(
            @PathVariable("numeroConta") Long numeroConta,
            @RequestParam(value = "dataInicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
            @RequestParam(value = "dataFim", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFim,
            @RequestParam(value = "nomeOperador", required = false) String nomeOperador) {

        DadosEntradaDTO dadosEntrada = new DadosEntradaDTO(numeroConta, dataInicio, dataFim, nomeOperador);

        ExtratoDTO extratoGerado = contaService.gerarExtrato(dadosEntrada);

        return extratoGerado;

    }
}
