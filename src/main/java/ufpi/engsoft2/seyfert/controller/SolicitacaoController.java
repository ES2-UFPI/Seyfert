package ufpi.engsoft2.seyfert.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<SolicitacaoDTO> cadastrarSolicitacao(@RequestBody SolicitacaoForm solicitacao,
            UriComponentsBuilder uriComponentsBuilder) {
        SolicitacaoDTO solicitacaoDTO = solicitacaoService.cadastrar(solicitacao);

        URI uri = uriComponentsBuilder.path("/solicitacao/{id}").buildAndExpand(solicitacaoDTO.getUuid()).toUri();

        return ResponseEntity.created(uri).body(solicitacaoDTO);
    }

    @GetMapping("/paciente/{uuidPaciente}")
    public ResponseEntity<Page<SolicitacaoDTO>> listarSolicitacoesPaciente(@PathVariable UUID uuidPaciente,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataParaAtendimento,
            @RequestParam(required = false) String nomeEspecialidade,
            @PageableDefault(sort = "dataParaAtendimento", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(solicitacaoService.listarSolicitacoesPaciente(uuidPaciente, dataParaAtendimento,
                nomeEspecialidade, pageable));
    }

    @GetMapping("/medico/{uuidMedico}")
    public ResponseEntity<Page<SolicitacaoDTO>> listarSolicitacoesMedico(@PathVariable UUID uuidMedico,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataParaAtendimento,
            @RequestParam(required = false) String nomeEspecialidade,
            @PageableDefault(sort = "dataParaAtendimento", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(solicitacaoService.listarSolicitacoesMedico(uuidMedico, dataParaAtendimento,
                nomeEspecialidade, pageable));
    }
}
