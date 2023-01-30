package ufpi.engsoft2.seyfert.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.service.consulta.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    
    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<ConsultaDTO> getConsulta(String id){
        UUID uuid = UUID.fromString(id);
        ConsultaDTO consultaDTO = consultaService.getConsulta(uuid);
        return ResponseEntity.ok(consultaDTO);
    }
}
