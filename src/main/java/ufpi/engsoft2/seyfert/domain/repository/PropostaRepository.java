package ufpi.engsoft2.seyfert.domain.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.enums.SituacaoProposta;
import ufpi.engsoft2.seyfert.domain.model.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{
    Page<Proposta> findBySituacao(SituacaoProposta situacao, Pageable paginacao);
    Page<Proposta> findBySolicitacaoUuidAndSituacao(UUID solicitacaoUuid, SituacaoProposta situacao, Pageable paginacao);
    Page<Proposta> findBySolicitacaoUuidAndDataAtendimento(UUID solicitacaoUuid, LocalDate situacao, Pageable paginacao);
    Page<Proposta> findByMedicoUuidAndDataAtendimento(UUID medicoUuid, LocalDate situacao, Pageable paginacao);
    Page<Proposta> findByMedicoUuidAndSituacao(UUID medicoUuid, SituacaoProposta situacao, Pageable paginacao);
    Page<Proposta> findByDataAtendimento(LocalDate dataAtendimento, Pageable paginacao);
    Proposta findByUuid(UUID propostaUuid);
}
