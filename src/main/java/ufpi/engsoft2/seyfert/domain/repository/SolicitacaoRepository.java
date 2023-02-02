package ufpi.engsoft2.seyfert.domain.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.model.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
=======
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.model.EspecialidadeMedica;
import ufpi.engsoft2.seyfert.domain.model.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    Page<Solicitacao> findByPacienteUuid(UUID uuidPaciente, Pageable pageable);
    Page<Solicitacao> findByPacienteUuidAndDataParaAtendimento(UUID uuidPaciente, LocalDate dataParaAtendimento, Pageable pageable);
    Page<Solicitacao> findByPacienteUuidAndEspecialidadeMedicaUuid(UUID uuidPaciente, UUID uuidEspecialidade, Pageable pageable);
    Page<Solicitacao> findByPacienteUuidAndDataParaAtendimentoAndEspecialidadeMedicaUuid(UUID uuidPaciente, LocalDate dataParaAtendimento, UUID uuidEspecialidade, Pageable pageable);
    Page<Solicitacao> findByEspecialidadeMedicaUuid(UUID uuidEspecialidade, Pageable pageable);
    Page<Solicitacao> findByEspecialidadeMedicaIn(List<EspecialidadeMedica> especialidades, Pageable pageable);
    Page<Solicitacao> findByEspecialidadeMedicaInAndDataParaAtendimento(List<EspecialidadeMedica> especialidades, LocalDate dataParaAtendimento, Pageable pageable);
    Page<Solicitacao> findByEspecialidadeMedicaUuidAndDataParaAtendimento(UUID uuidEspecialidade, LocalDate dataParaAtendimento, Pageable pageable);
>>>>>>> 6fdd39907ac372e3afb15ff20cfe3f4871c4b3b9
}
