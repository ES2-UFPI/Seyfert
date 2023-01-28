package ufpi.engsoft2.seyfert.service.proposta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ufpi.engsoft2.seyfert.domain.dto.PropostaDTO;

public interface PropostaService {
    Page<PropostaDTO> listarPropostasCliente(Pageable paginacao);
}
