package ufpi.engsoft2.seyfert.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ufpi.engsoft2.seyfert.domain.dto.SolicitacaoDTO;
import ufpi.engsoft2.seyfert.domain.form.SolicitacaoForm;
import ufpi.engsoft2.seyfert.service.solicitacao.SolicitacaoService;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {
    
    @Autowired
    private SolicitacaoService solicitacaoService;

    @PostMapping
    public ResponseEntity<SolicitacaoDTO> cadastrarSolicitacao(@RequestBody SolicitacaoForm solicitacao, UriComponentsBuilder uriComponentsBuilder){
        SolicitacaoDTO solicitacaoDTO = solicitacaoService.cadastrar(solicitacao);

		URI uri = uriComponentsBuilder.path("/solicitacao/{id}").buildAndExpand(solicitacaoDTO.getUuid()).toUri();

		return ResponseEntity.created(uri).body(solicitacaoDTO);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<SolicitacaoDTO> listarSolicitacoesPaciente(){
        return ResponseEntity.ok().build();
    }
}
