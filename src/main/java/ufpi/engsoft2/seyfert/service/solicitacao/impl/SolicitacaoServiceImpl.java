package ufpi.engsoft2.seyfert.service.solicitacao.impl;

import java.time.LocalDate;
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
import ufpi.engsoft2.seyfert.domain.model.Solicitacao;
import ufpi.engsoft2.seyfert.domain.repository.EspecialidadeMedicaRepository;
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

    @Override
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
}
