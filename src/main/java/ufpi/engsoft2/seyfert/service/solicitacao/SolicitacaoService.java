package ufpi.engsoft2.seyfert.service.solicitacao;

import ufpi.engsoft2.seyfert.domain.dto.SolicitacaoDTO;
import ufpi.engsoft2.seyfert.domain.form.SolicitacaoForm;

public interface SolicitacaoService {
    SolicitacaoDTO cadastrar(SolicitacaoForm solicitacao);
}
