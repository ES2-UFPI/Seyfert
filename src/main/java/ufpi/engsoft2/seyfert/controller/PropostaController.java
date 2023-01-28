package ufpi.engsoft2.seyfert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufpi.engsoft2.seyfert.domain.dto.PropostaDTO;
import ufpi.engsoft2.seyfert.service.proposta.PropostaService;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;
    
    @GetMapping
    public ResponseEntity<Page<PropostaDTO>> listarPropostasPacientes(
                                            @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, 
                                                            page = 0, size = 10) Pageable paginacao){
        return ResponseEntity.ok(propostaService.listarPropostasCliente(paginacao));
    }
}
