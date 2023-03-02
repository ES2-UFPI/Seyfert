package ufpi.engsoft2.seyfert.service.proposta;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ufpi.engsoft2.seyfert.domain.dto.PropostaDTO;
import ufpi.engsoft2.seyfert.domain.dto.ResponsePadraoParaAtualizacaoRecursoDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoProposta;
import ufpi.engsoft2.seyfert.domain.form.PropostaForm;

public interface PropostaService {
    Page<PropostaDTO> listarPropostasCliente(SituacaoProposta situacao, UUID solicitacao, UUID medico, LocalDate dataAtendimento, Pageable paginacao);
    ResponsePadraoParaAtualizacaoRecursoDTO adicionarPropostas(UUID solicitacaoUuid, UUID medico, List<PropostaForm> propostas);
}
