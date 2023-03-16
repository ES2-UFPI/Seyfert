package ufpi.engsoft2.seyfert.service.consulta.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.domain.dto.ResponsePadraoParaAtualizacaoRecursoDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoConsulta;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoPagamento;
import ufpi.engsoft2.seyfert.domain.form.HorarioDisponivelMedicoForm;
import ufpi.engsoft2.seyfert.domain.model.Consulta;
import ufpi.engsoft2.seyfert.domain.model.HorarioDisponivelMedico;
import ufpi.engsoft2.seyfert.domain.repository.ConsultaRepository;
import ufpi.engsoft2.seyfert.domain.repository.MedicoRepository;
import ufpi.engsoft2.seyfert.domain.repository.PacienteRepository;
import ufpi.engsoft2.seyfert.service.consulta.ConsultaMapper;
import ufpi.engsoft2.seyfert.service.consulta.ConsultaService;
import ufpi.engsoft2.seyfert.service.consulta.HorarioDisponivelMapper;
import ufpi.engsoft2.seyfert.shared.exception.BussinesRuleException;
import ufpi.engsoft2.seyfert.shared.exception.EntityNotFoundException;
import ufpi.engsoft2.seyfert.domain.model.Medico;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {
    @Autowired
    private HorarioDisponivelMapper horarioDisponivelMapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaMapper consultaMapper;

    @Override
    public ConsultaDTO getConsulta(UUID uuid) {
        Consulta consulta = consultaRepository.findByUuid(uuid);
        if (consulta == null) {
            throw new EntityNotFoundException("Consulta não encontrada");
        }
        return consultaMapper.toDto(consulta);
    }

    @Override
    public Page<ConsultaDTO> listarConsultasPaciente(UUID pacienteUuid, SituacaoConsulta situacaoConsulta,
        SituacaoPagamento situacaoPagamento, LocalDate dataAtendimento, Pageable pageable) {
        if (pacienteUuid == null) {
            throw new BussinesRuleException("O uuid de paciente não pode ser nulo");
        }
        if (pacienteRepository.findByUuid(pacienteUuid) == null) {
            throw new EntityNotFoundException("Paciente não encontrado");
        }
        Page<Consulta> pageConsultas;
        if (situacaoConsulta == null && situacaoPagamento == null && dataAtendimento == null) {
            pageConsultas = consultaRepository.findByPacienteUuid(pacienteUuid, pageable);
        } else if (situacaoConsulta == null && situacaoPagamento == null && dataAtendimento != null) {
            pageConsultas = consultaRepository.findByPacienteUuidAndDataAtendimento(pacienteUuid, dataAtendimento, pageable);
        } else if (situacaoConsulta == null && situacaoPagamento != null && dataAtendimento == null) {
            pageConsultas = consultaRepository.findByPagamentoConsulta_SituacaoAndPacienteUuid(situacaoPagamento, pacienteUuid, pageable);
        } else if (situacaoConsulta != null && situacaoPagamento == null && dataAtendimento == null) {
            pageConsultas = consultaRepository.findBySituacaoAndPacienteUuid(situacaoConsulta, pacienteUuid, pageable);
        } else if (situacaoConsulta != null && situacaoPagamento != null && dataAtendimento == null) {
            pageConsultas = consultaRepository.findByPagamentoConsulta_SituacaoAndSituacaoAndPacienteUuid(situacaoPagamento, situacaoConsulta, pacienteUuid, pageable);
        } else if (situacaoConsulta == null && situacaoPagamento != null && dataAtendimento != null) {
            pageConsultas = consultaRepository.findByPagamentoConsulta_SituacaoAndPacienteUuidAndDataAtendimento(situacaoPagamento, pacienteUuid, dataAtendimento, pageable);
        } else if (situacaoConsulta != null && situacaoPagamento == null && dataAtendimento != null) {
            pageConsultas = consultaRepository.findBySituacaoAndPacienteUuidAndDataAtendimento(situacaoConsulta, pacienteUuid, dataAtendimento, pageable);
        } else {
            pageConsultas = consultaRepository.findByPagamentoConsulta_SituacaoAndSituacaoAndPacienteUuidAndDataAtendimento(situacaoPagamento, situacaoConsulta, pacienteUuid, dataAtendimento, pageable);
        }
            if (pageConsultas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma consulta encontrada");
        }
        return pageConsultas.map(consultaMapper::toDto);
    }

    @Override
    public Page<ConsultaDTO> listarConsultasMedico(UUID medicoUuid, SituacaoConsulta situacaoConsulta,
            SituacaoPagamento situacaoPagamento, LocalDate dataAtendimento, Pageable pageable) {
        if (medicoUuid == null) {
            throw new BussinesRuleException("O uuid de medico não pode ser nulo");
        }
        if (medicoRepository.findByUuid(medicoUuid) == null) {
            throw new EntityNotFoundException("Médico não encontrado");
        }
        Page<Consulta> pageConsultas;
        if (situacaoConsulta == null && situacaoPagamento == null && dataAtendimento == null) {
            pageConsultas = consultaRepository.findByMedicoUuid(medicoUuid, pageable);
        } else if (situacaoConsulta == null && situacaoPagamento == null && dataAtendimento != null) {
            pageConsultas = consultaRepository.findByMedicoUuidAndDataAtendimento(medicoUuid, dataAtendimento, pageable);
        } else if (situacaoConsulta == null && situacaoPagamento != null && dataAtendimento == null) {
            pageConsultas = consultaRepository.findByPagamentoConsulta_SituacaoAndMedicoUuid(situacaoPagamento, medicoUuid, pageable);
        } else if (situacaoConsulta != null && situacaoPagamento == null && dataAtendimento == null) {
            pageConsultas = consultaRepository.findBySituacaoAndMedicoUuid(situacaoConsulta, medicoUuid, pageable);
        } else if (situacaoConsulta != null && situacaoPagamento != null && dataAtendimento == null) {
            pageConsultas = consultaRepository.findByPagamentoConsulta_SituacaoAndSituacaoAndMedicoUuid(situacaoPagamento, situacaoConsulta, medicoUuid, pageable);
        } else if (situacaoConsulta == null && situacaoPagamento != null && dataAtendimento != null) {
            pageConsultas = consultaRepository.findByPagamentoConsulta_SituacaoAndMedicoUuidAndDataAtendimento(situacaoPagamento, medicoUuid, dataAtendimento, pageable);
        } else if (situacaoConsulta != null && situacaoPagamento == null && dataAtendimento != null) {
            pageConsultas = consultaRepository.findBySituacaoAndMedicoUuidAndDataAtendimento(situacaoConsulta, medicoUuid, dataAtendimento, pageable);
        } else {
            pageConsultas = consultaRepository.findByPagamentoConsulta_SituacaoAndSituacaoAndMedicoUuidAndDataAtendimento(situacaoPagamento, situacaoConsulta, medicoUuid, dataAtendimento, pageable);
        }
        if (pageConsultas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma consulta encontrada");
        }
        return pageConsultas.map(consultaMapper::toDto);
    }

    @Override
    public ResponsePadraoParaAtualizacaoRecursoDTO validarConsulta(UUID consultaUuid, String codigoValidacao) {
       Consulta consulta = consultaRepository.findByUuid(consultaUuid);

       if(consulta == null){
            throw new EntityNotFoundException("Nenhuma consulta encontrada para o id "+consultaUuid);
       }

       if(codigoValidacao.length() != 11){
            throw new BussinesRuleException("O código enviado não tem a estrutura esperada");
       }

       if(!consulta.getCodigoVerificacao().equalsIgnoreCase(codigoValidacao)){
            throw new BussinesRuleException("Código enviado não válido para validação da consulta "+consultaUuid);
       }else {
           consulta.setSituacao(SituacaoConsulta.VALIDADA);
           consultaRepository.save(consulta);

           return new ResponsePadraoParaAtualizacaoRecursoDTO("Consulta validada");
       }
    }

    public ResponsePadraoParaAtualizacaoRecursoDTO cadastrarHorarioDisponivel(UUID medicoUuid, HorarioDisponivelMedicoForm horarioForm){
        Medico medico = medicoRepository.findByUuid(medicoUuid);
        if (medico == null) {
            throw new EntityNotFoundException("Médico não encontrado");
        }

        HorarioDisponivelMedico horarioDisponivel = horarioDisponivelMapper.toModel(horarioForm);

        List<HorarioDisponivelMedico> horarios = medico.getHorariosDisponiveis();

        for (HorarioDisponivelMedico horario : horarios) {
            if (horario.equals(horarioDisponivel)) {
                throw new BussinesRuleException("Horario já cadastrado.");
            }
        }

        horarios.add(horarioDisponivel);

        medico.setHorariosDisponiveis(horarios);

        medicoRepository.save(medico);

        return new ResponsePadraoParaAtualizacaoRecursoDTO("Horário cadastrado com sucesso");
    }

    @Override
    public ResponsePadraoParaAtualizacaoRecursoDTO adicionarDetalhes(UUID consultaUuid, String detalhes) {
       Consulta consulta = consultaRepository.findByUuid(consultaUuid);

       if(detalhes == null){
           throw new BussinesRuleException("Os detalhes da consulta não podem ser vazia.");
       }

       if(consulta == null){
            throw new EntityNotFoundException("Nenhuma consulta encontrada com o id "+consultaUuid);
       }

       if(!consulta.getSituacao().equals(SituacaoConsulta.VALIDADA)){
            throw new BussinesRuleException("A consulta não encontra-se validada para preencher detalhes");
       }

       consulta.setDescricaoMedica(detalhes);
       consultaRepository.save(consulta);
       
       return new ResponsePadraoParaAtualizacaoRecursoDTO("Detalhes adicionados com sucesso na consulta");
    }

    @Override
    @Transactional
    public ResponsePadraoParaAtualizacaoRecursoDTO cancelarConsulta(UUID consultaUuid) {
        Consulta consulta = consultaRepository.findByUuid(consultaUuid);
        if (consulta == null) {
            throw new EntityNotFoundException("Nenhuma consulta encontrada para o id "+consultaUuid);
        }
        if (consulta.getSituacao() == SituacaoConsulta.CANCELADA) {
            throw new BussinesRuleException("Consulta já está cancelada");
        }
        if (consulta.getSituacao() == SituacaoConsulta.FINALIZADA) {
            throw new BussinesRuleException("Não é possível cancelar uma consulta que já foi finalizada");
        }
        if (consulta.getSituacao() == SituacaoConsulta.VALIDADA) {
            throw new BussinesRuleException("Não é possível cancelar uma consulta que já foi validada");
        }
        if (consulta.getSituacao() == SituacaoConsulta.ESTORNADA) {
            throw new BussinesRuleException("Não é possível cancelar uma consulta que já foi estornada");
        }
        if (consulta.getSituacao() == SituacaoConsulta.VENCIDA) {
            throw new BussinesRuleException("Não é possível cancelar uma consulta que está vencida");
        }
        consulta.setSituacao(SituacaoConsulta.CANCELADA);
        consultaRepository.save(consulta);

        return new ResponsePadraoParaAtualizacaoRecursoDTO("Consulta cancelada");
    }
    
    public List<ConsultaDTO> listarHistoricoConsultas(UUID pacienteUuid) {
        // Verifica se o paciente existe
        Paciente paciente = pacienteRepository.findByUuid(pacienteUuid);
        if (paciente == null) {
            throw new EntityNotFoundException("Paciente não encontrado");
        }
        
        // Busca as consultas
        List<Consulta> consultas = consultaRepository.findByPacienteUuid(pacienteUuid);
        
        // Verifica se existem consultas
        if (consultas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma consulta encontrada para o paciente");
        }
        
        // Converte as consultas em DTOs
        List<ConsultaDTO> consultasDTO = consultas.stream()
                .map(consultaMapper::toDto)
                .collect(Collectors.toList());
        
        return consultasDTO;
    }
}
