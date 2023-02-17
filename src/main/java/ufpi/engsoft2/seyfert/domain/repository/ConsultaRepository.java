package ufpi.engsoft2.seyfert.domain.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.enums.SituacaoConsulta;
import ufpi.engsoft2.seyfert.domain.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    Consulta findByUuid(UUID uuid);

Page<Consulta> findByPacienteUuid(UUID pacienteUuid, Pageable pageable);

Page<Consulta> findByPacienteUuidAndDataAtendimento(UUID pacienteUuid, LocalDate dataAtendimento, Pageable pageable);

Page<Consulta> findByPacienteUuidAndSituacao(UUID pacienteUuid, SituacaoConsulta situacaoConsulta, Pageable pageable);

Page<Consulta> findByPacienteUuidAndSituacaoAndDataAtendimento(UUID pacienteUuid, SituacaoConsulta situacaoConsulta,
        LocalDate dataAtendimento, Pageable pageable);

Page<Consulta> findByMedicoUuid(UUID medicoUuid, Pageable pageable);

Page<Consulta> findByMedicoUuidAndDataAtendimento(UUID medicoUuid, LocalDate dataAtendimento, Pageable pageable);

Page<Consulta> findByMedicoUuidAndSituacao(UUID medicoUuid, SituacaoConsulta situacaoConsulta, Pageable pageable);

Page<Consulta> findByMedicoUuidAndSituacaoAndDataAtendimento(UUID medicoUuid, SituacaoConsulta situacaoConsulta,
        LocalDate dataAtendimento, Pageable pageable);
}
