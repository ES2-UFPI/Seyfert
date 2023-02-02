package ufpi.engsoft2.seyfert.service.solicitacao;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ufpi.engsoft2.seyfert.domain.dto.SolicitacaoDTO;
import ufpi.engsoft2.seyfert.domain.form.SolicitacaoForm;

public interface SolicitacaoService {
    SolicitacaoDTO cadastrar(SolicitacaoForm solicitacao);
    Page<SolicitacaoDTO> listarSolicitacoesPaciente(UUID uuidPaciente, LocalDate dataParaAtendimento, String nomeEspecialidade, Pageable pageable);
    Page<SolicitacaoDTO> listarSolicitacoesMedico(UUID uuidMedico, LocalDate dataParaAtendimento, String nomeEspecialidade, Pageable pageable);
}
