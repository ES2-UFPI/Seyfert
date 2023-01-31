package ufpi.engsoft2.seyfert.service.solicitacao;

import java.util.List;

import ufpi.engsoft2.seyfert.domain.dto.SolicitacaoDTO;
import ufpi.engsoft2.seyfert.domain.form.SolicitacaoForm;

public interface SolicitacaoService {
    SolicitacaoDTO cadastrar(SolicitacaoForm solicitacao);
    public List<SolicitacaoDTO> listarSolicitacoesPaciente(Long IdPaciente);
}
