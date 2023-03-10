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
import ufpi.engsoft2.seyfert.domain.model.Solicitacao;
import ufpi.engsoft2.seyfert.domain.repository.EspecialidadeMedicaRepository;
import ufpi.engsoft2.seyfert.domain.repository.MedicoRepository;
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
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeMedicaRepository especialidadeMedicaRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private SolicitacaoMapper solicitacaoMapper;

    @Override
    public SolicitacaoDTO cadastrar(SolicitacaoForm form) {
        Solicitacao solicitacao = solicitacaoMapper.toModel(form);
        solicitacao.setSituacaoSolicitacao(SituacaoSolicitacao.SOLICITADA);

        // Esse dois setters devem ser ajustados para quando tivermos o cadastro de
        // paciente e especialidade
        // solicitacao.setPaciente(new Paciente());
        // solicitacao.setEspecialidadeMedica(new EspecialidadeMedica());

        SolicitacaoDTO solicitacaoDTO = solicitacaoMapper.toDto(solicitacaoRepository.save(solicitacao));
        solicitacaoDTO.setNomePaciente("Edson Arantes do Nascimento");
        solicitacaoDTO.setNomeEspecialidade("Ortopedia");

        return solicitacaoDTO;
    }

    public Page<SolicitacaoDTO> listarSolicitacoesPaciente(UUID uuidPaciente, LocalDate dataParaAtendimento,
            String nomeEspecialidade, Pageable pageable) {
        Page<Solicitacao> pageSolicitacoes;
        if (uuidPaciente == null) {
            throw new BussinesRuleException("O uuid do paciente n??o pode ser nulo");
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
            throw new EntityNotFoundException("N??o foram encontradas solicita????es para o paciente informado");
        }
        return pageSolicitacoes.map(this.solicitacaoMapper::toDto);
    }

    @Override
    public Page<SolicitacaoDTO> listarSolicitacoesMedico(UUID uuidMedico, LocalDate dataParaAtendimento,
            String nomeEspecialidade, Pageable pageable) {
        Page<Solicitacao> pageSolicitacoes;
        if (uuidMedico == null) {
            throw new BussinesRuleException("O uuid do m??dico n??o pode ser nulo");
        }
        Medico medico = medicoRepository.findByUuid(uuidMedico);
        if (medico == null) {
            throw new EntityNotFoundException("N??o foi encontrado um m??dico com o uuid informado");
        }
        List<EspecialidadeMedica> especialidadesMedicas = medico.getEspecialidades();
        if (especialidadesMedicas.isEmpty()) {
            throw new EntityNotFoundException("O m??dico informado n??o possui especialidades cadastradas");
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
            throw new EntityNotFoundException("N??o foram encontradas solicita????es para o m??dico informado");
        }
        return pageSolicitacoes.map(this.solicitacaoMapper::toDto);
    }
}
