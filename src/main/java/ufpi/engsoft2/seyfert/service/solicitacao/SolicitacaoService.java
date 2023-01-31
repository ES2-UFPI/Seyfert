package ufpi.engsoft2.seyfert.service.solicitacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ufpi.engsoft2.seyfert.domain.dto.SolicitacaoDTO;
import ufpi.engsoft2.seyfert.domain.form.SolicitacaoForm;

public interface SolicitacaoService {
    SolicitacaoDTO cadastrar(SolicitacaoForm solicitacao);
    public Page<SolicitacaoDTO> listarSolicitacoesPaciente(Long idPaciente, Pageable pageable);
}
