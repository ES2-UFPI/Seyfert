package ufpi.engsoft2.seyfert.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.model.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    public Page<Solicitacao> findByPacienteId(Long idPaciente, Pageable pageable);
}
