package ufpi.engsoft2.seyfert.controller;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufpi.engsoft2.seyfert.domain.dto.PropostaDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoProposta;
import ufpi.engsoft2.seyfert.service.proposta.PropostaService;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;
    
    @GetMapping
    public ResponseEntity<Page<PropostaDTO>> listarPropostasPacientes(
                                            @RequestParam(required = false, name = "solicitacao") UUID solicitacao,
                                            @RequestParam(required = false, name = "medico") UUID medico,
                                            @RequestParam(required = false, name = "dataAtendimento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAtendimento,  
                                            @RequestParam(required = false, name = "situacao") SituacaoProposta situacao, 
                                            @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao){
        return ResponseEntity.ok(propostaService.listarPropostasCliente(situacao, solicitacao, medico, dataAtendimento, paginacao));
    }
}
