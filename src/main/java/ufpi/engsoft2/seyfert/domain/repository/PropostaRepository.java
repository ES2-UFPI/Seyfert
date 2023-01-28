package ufpi.engsoft2.seyfert.domain.repository;

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
}
