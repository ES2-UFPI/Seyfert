package ufpi.engsoft2.seyfert.service.solicitacao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.dto.SolicitacaoDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoSolicitacao;
import ufpi.engsoft2.seyfert.domain.form.SolicitacaoForm;
import ufpi.engsoft2.seyfert.domain.model.EspecialidadeMedica;
import ufpi.engsoft2.seyfert.domain.model.Paciente;
import ufpi.engsoft2.seyfert.domain.model.Solicitacao;
import ufpi.engsoft2.seyfert.domain.repository.SolicitacaoRepository;
import ufpi.engsoft2.seyfert.service.solicitacao.SolicitacaoMapper;
import ufpi.engsoft2.seyfert.service.solicitacao.SolicitacaoService;
import ufpi.engsoft2.seyfert.shared.exception.BussinesRuleException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoServiceImpl implements SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private SolicitacaoMapper solicitacaoMapper;

    @Override
    public SolicitacaoDTO cadastrar(SolicitacaoForm form) {
        Solicitacao solicitacao = solicitacaoMapper.toModel(form);
        solicitacao.setSituacaoSolicitacao(SituacaoSolicitacao.SOLICITADA);

        //Esse dois setters devem ser ajustados para quando tivermos o cadastro de paciente e especialidade
        // solicitacao.setPaciente(new Paciente());
        // solicitacao.setEspecialidadeMedica(new EspecialidadeMedica());
        
        SolicitacaoDTO solicitacaoDTO = solicitacaoMapper.toDto(solicitacaoRepository.save(solicitacao));
        solicitacaoDTO.setNomePaciente("Edson Arantes do Nascimento");
        solicitacaoDTO.setNomeEspecialidade("Ortopedia");

        return solicitacaoDTO;
    }

    public Page<SolicitacaoDTO> listarSolicitacoesPaciente(Long idPaciente, Pageable pageable) {
        List<Solicitacao> solicitacoes = solicitacaoRepository.findByPacienteId(idPaciente, pageable).getContent();
        Page<SolicitacaoDTO> pageSolicitacaoDTO = new PageImpl<>(solicitacaoMapper.toDto(solicitacoes), pageable, solicitacoes.size());
        if (pageSolicitacaoDTO.isEmpty()){
            throw new BussinesRuleException("Nenhuma solicitação encontrada");
        }
        return pageSolicitacaoDTO;
    }
}
