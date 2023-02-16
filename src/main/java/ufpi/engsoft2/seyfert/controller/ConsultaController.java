package ufpi.engsoft2.seyfert.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.domain.dto.ResponsePadraoParaAtualizacaoRecursoDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoConsulta;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoPagamento;
import ufpi.engsoft2.seyfert.service.consulta.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    
    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ConsultaDTO> getConsulta(@PathVariable UUID uuid){
        ConsultaDTO consultaDTO = consultaService.getConsulta(uuid);

        return ResponseEntity.ok(consultaDTO);
    }

    @PatchMapping("/{consultaUuid}")
    public ResponseEntity<ResponsePadraoParaAtualizacaoRecursoDTO> validarConsulta(@PathVariable UUID consultaUuid, @RequestParam String codigo){

        return ResponseEntity.ok(consultaService.validarConsulta(consultaUuid, codigo));
    }
}
