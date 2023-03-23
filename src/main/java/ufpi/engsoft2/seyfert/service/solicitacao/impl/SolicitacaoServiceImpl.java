package ufpi.engsoft2.seyfert.service.solicitacao.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.dto.SolicitacaoDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoSolicitacao;
import ufpi.engsoft2.seyfert.domain.form.SolicitacaoForm;
import ufpi.engsoft2.seyfert.domain.model.EspecialidadeMedica;
import ufpi.engsoft2.seyfert.domain.model.Medico;
import ufpi.engsoft2.seyfert.domain.model.Paciente;
import ufpi.engsoft2.seyfert.domain.model.Solicitacao;
import ufpi.engsoft2.seyfert.domain.repository.EspecialidadeMedicaRepository;
import ufpi.engsoft2.seyfert.domain.repository.MedicoRepository;
import ufpi.engsoft2.seyfert.domain.repository.PacienteRepository;
import ufpi.engsoft2.seyfert.domain.repository.SolicitacaoRepository;
import ufpi.engsoft2.seyfert.service.solicitacao.SolicitacaoMapper;
import ufpi.engsoft2.seyfert.service.solicitacao.SolicitacaoService;
import ufpi.engsoft2.seyfert.shared.exception.BussinesRuleException;
import ufpi.engsoft2.seyfert.shared.exception.EntityNotFoundException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoServiceImpl implements SolicitacaoService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeMedicaRepository especialidadeMedicaRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private SolicitacaoMapper solicitacaoMapper;

    @Override
    public SolicitacaoDTO cadastrar(SolicitacaoForm form) {
        Paciente paciente = pacienteRepository.findByUuid(form.getPacienteUuid());
        if (paciente == null) {
            throw new EntityNotFoundException("Paciente não encontrado");
        }
        EspecialidadeMedica especialidadeMedica = especialidadeMedicaRepository.findByUuid(form.getEspecialidadeMedicaUuid());
        if (especialidadeMedica == null) {
            throw new EntityNotFoundException("Especialidade médica não encontrada");
        }

        Solicitacao solicitacao = solicitacaoMapper.toModel(form);
        solicitacao.setSituacaoSolicitacao(SituacaoSolicitacao.SOLICITADA);
        solicitacao.setPaciente(paciente);
        solicitacao.setEspecialidadeMedica(especialidadeMedica);

        SolicitacaoDTO solicitacaoDTO = solicitacaoMapper.toDto(solicitacaoRepository.save(solicitacao));

        // Esse dois setters devem ser ajustados para quando tivermos o cadastro de
        // paciente e especialidade
        // solicitacao.setPaciente(new Paciente());
        // solicitacao.setEspecialidadeMedica(new EspecialidadeMedica());

        return solicitacaoDTO;
    }

    public Page<SolicitacaoDTO> listarSolicitacoesPaciente(UUID uuidPaciente, LocalDate dataParaAtendimento,
            String nomeEspecialidade, Pageable pageable) {
        Page<Solicitacao> pageSolicitacoes;
        if (uuidPaciente == null) {
            throw new BussinesRuleException("O uuid do paciente não pode ser nulo");
        }
        if (dataParaAtendimento == null && nomeEspecialidade == null) {
            pageSolicitacoes = solicitacaoRepository.findByPacienteUuid(uuidPaciente, pageable);
        } else if (dataParaAtendimento != null && nomeEspecialidade == null) {
            pageSolicitacoes = solicitacaoRepository.findByPacienteUuidAndDataParaAtendimento(uuidPaciente,
                    dataParaAtendimento, pageable);
        } else if (dataParaAtendimento == null && nomeEspecialidade != null) {
            UUID uuidEspecialidade = especialidadeMedicaRepository.findByNomeEspecialidade(nomeEspecialidade).getUuid();
            pageSolicitacoes = solicitacaoRepository.findByPacienteUuidAndEspecialidadeMedicaUuid(uuidPaciente,
                    uuidEspecialidade, pageable);
        } else {
            UUID uuidEspecialidade = especialidadeMedicaRepository.findByNomeEspecialidade(nomeEspecialidade).getUuid();
            pageSolicitacoes = solicitacaoRepository.findByPacienteUuidAndDataParaAtendimentoAndEspecialidadeMedicaUuid(
                    uuidPaciente, dataParaAtendimento, uuidEspecialidade, pageable);
        }
        if (pageSolicitacoes.isEmpty()) {
            throw new EntityNotFoundException("Não foram encontradas solicitações para o paciente informado");
        }
        return pageSolicitacoes.map(this.solicitacaoMapper::toDto);
    }

    @Override
    public Page<SolicitacaoDTO> listarSolicitacoesMedico(UUID uuidMedico, LocalDate dataParaAtendimento,
            String nomeEspecialidade, Pageable pageable) {
        Page<Solicitacao> pageSolicitacoes;
        if (uuidMedico == null) {
            throw new BussinesRuleException("O uuid do médico não pode ser nulo");
        }
        Medico medico = medicoRepository.findByUuid(uuidMedico);
        if (medico == null) {
            throw new EntityNotFoundException("Não foi encontrado um médico com o uuid informado");
        }
        List<EspecialidadeMedica> especialidadesMedicas = medico.getEspecialidades();
        if (especialidadesMedicas.isEmpty()) {
            throw new EntityNotFoundException("O médico informado não possui especialidades cadastradas");
        }
        if (dataParaAtendimento == null && nomeEspecialidade == null) {
            pageSolicitacoes = solicitacaoRepository.findByEspecialidadeMedicaIn(especialidadesMedicas, pageable);
        } else if (dataParaAtendimento != null && nomeEspecialidade == null) {
            pageSolicitacoes = solicitacaoRepository.findByEspecialidadeMedicaInAndDataParaAtendimento(
                    especialidadesMedicas, dataParaAtendimento, pageable);
        } else if (dataParaAtendimento == null && nomeEspecialidade != null) {
            UUID uuidEspecialidade = especialidadeMedicaRepository.findByNomeEspecialidade(nomeEspecialidade).getUuid();
            pageSolicitacoes = solicitacaoRepository.findByEspecialidadeMedicaUuid(uuidEspecialidade, pageable);
        } else {
            UUID uuidEspecialidade = especialidadeMedicaRepository.findByNomeEspecialidade(nomeEspecialidade).getUuid();
            pageSolicitacoes = solicitacaoRepository.findByEspecialidadeMedicaUuidAndDataParaAtendimento(uuidEspecialidade, dataParaAtendimento, pageable);
        }
        if (pageSolicitacoes.isEmpty()) {
            throw new EntityNotFoundException("Não foram encontradas solicitações para o médico informado");
        }
        return pageSolicitacoes.map(this.solicitacaoMapper::toDto);
    }
}
