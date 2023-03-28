package ufpi.engsoft2.seyfert.controller;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.domain.dto.ResponsePadraoParaAtualizacaoRecursoDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoConsulta;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoPagamento;
import ufpi.engsoft2.seyfert.domain.form.ConsultaForm;
import ufpi.engsoft2.seyfert.domain.form.HorarioDisponivelMedicoForm;
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

    @GetMapping("/paciente/{pacienteUuid}")
    public ResponseEntity<Page<ConsultaDTO>> listarConsultasPaciente(
            @PathVariable UUID pacienteUuid,
            @RequestParam(required = false) SituacaoConsulta situacaoConsulta,
            @RequestParam(required = false) SituacaoPagamento situacaoPagamento,
            @RequestParam(required = false) LocalDate dataAtendimento,
            @PageableDefault(sort = "dataAtendimento", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
            ){
        Page<ConsultaDTO> consultas = consultaService.listarConsultasPaciente(pacienteUuid, situacaoConsulta, situacaoPagamento, dataAtendimento, pageable);

        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/medico/{medicoUuid}")
    public ResponseEntity<Page<ConsultaDTO>> listarConsultasMedico(
            @PathVariable UUID medicoUuid,
            @RequestParam(required = false) SituacaoConsulta situacaoConsulta,
            @RequestParam(required = false) SituacaoPagamento situacaoPagamento,
            @RequestParam(required = false) LocalDate dataAtendimento,
            @PageableDefault(sort = "dataAtendimento", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
            ){
        Page<ConsultaDTO> consultas = consultaService.listarConsultasMedico(medicoUuid, situacaoConsulta, situacaoPagamento, dataAtendimento, pageable);

        return ResponseEntity.ok(consultas);
    }

    @PatchMapping("/{consultaUuid}")
    public ResponseEntity<ResponsePadraoParaAtualizacaoRecursoDTO> validarConsulta(@PathVariable UUID consultaUuid, @RequestParam String codigo){

        return ResponseEntity.ok(consultaService.validarConsulta(consultaUuid, codigo));
    }

    @PostMapping
    public ResponseEntity<ResponsePadraoParaAtualizacaoRecursoDTO> agendarConsulta(@RequestBody ConsultaForm consultaForm,
    UriComponentsBuilder uriComponentsBuilder){
        ResponsePadraoParaAtualizacaoRecursoDTO resposta = consultaService.agendarConsulta(consultaForm);

        return ResponseEntity.created(null).body(resposta);
    }

    @PatchMapping("/{consultaUuid}/cancelar")
    public ResponseEntity<ResponsePadraoParaAtualizacaoRecursoDTO> cancelarConsulta(@PathVariable UUID consultaUuid){
        return ResponseEntity.ok(consultaService.cancelarConsulta(consultaUuid));
    }

    @PostMapping("/horarios/{uuidMedico}")
    public ResponseEntity<ResponsePadraoParaAtualizacaoRecursoDTO> cadastrarHorarioDisponivel(
        @PathVariable UUID uuidMedico,
        @RequestBody HorarioDisponivelMedicoForm horario){

        ResponsePadraoParaAtualizacaoRecursoDTO resposta = consultaService.cadastrarHorarioDisponivel(uuidMedico, horario);

        return ResponseEntity.created(null).body(resposta);
    }
}
