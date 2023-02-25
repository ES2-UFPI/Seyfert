package ufpi.engsoft2.seyfert.domain.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.enums.SituacaoConsulta;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoPagamento;
import ufpi.engsoft2.seyfert.domain.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    Consulta findByUuid(UUID uuid);

Page<Consulta> findByPacienteUuid(UUID pacienteUuid, Pageable pageable);

Page<Consulta> findByPacienteUuidAndDataAtendimento(UUID pacienteUuid, LocalDate dataAtendimento, Pageable pageable);

Page<Consulta> findByPagamentoConsulta_SituacaoAndPacienteUuid(SituacaoPagamento situacaoPagamento, UUID pacienteUuid,
        Pageable pageable);

Page<Consulta> findBySituacaoAndPacienteUuid(SituacaoConsulta situacaoConsulta, UUID pacienteUuid, Pageable pageable);

Page<Consulta> findByPagamentoConsulta_SituacaoAndSituacaoAndPacienteUuid(SituacaoPagamento situacaoPagamento,
        SituacaoConsulta situacaoConsulta, UUID pacienteUuid, Pageable pageable);

Page<Consulta> findByPagamentoConsulta_SituacaoAndPacienteUuidAndDataAtendimento(SituacaoPagamento situacaoPagamento,
        UUID pacienteUuid, LocalDate dataAtendimento, Pageable pageable);

Page<Consulta> findBySituacaoAndPacienteUuidAndDataAtendimento(SituacaoConsulta situacaoConsulta, UUID pacienteUuid,
        LocalDate dataAtendimento, Pageable pageable);

Page<Consulta> findByPagamentoConsulta_SituacaoAndSituacaoAndPacienteUuidAndDataAtendimento(
        SituacaoPagamento situacaoPagamento, SituacaoConsulta situacaoConsulta, UUID pacienteUuid,
        LocalDate dataAtendimento, Pageable pageable);

Page<Consulta> findByMedicoUuid(UUID medicoUuid, Pageable pageable);

Page<Consulta> findByMedicoUuidAndDataAtendimento(UUID medicoUuid, LocalDate dataAtendimento, Pageable pageable);

Page<Consulta> findByPagamentoConsulta_SituacaoAndMedicoUuid(SituacaoPagamento situacaoPagamento, UUID medicoUuid,
        Pageable pageable);

Page<Consulta> findBySituacaoAndMedicoUuid(SituacaoConsulta situacaoConsulta, UUID medicoUuid, Pageable pageable);

Page<Consulta> findByPagamentoConsulta_SituacaoAndSituacaoAndMedicoUuid(SituacaoPagamento situacaoPagamento,
        SituacaoConsulta situacaoConsulta, UUID medicoUuid, Pageable pageable);

Page<Consulta> findByPagamentoConsulta_SituacaoAndMedicoUuidAndDataAtendimento(SituacaoPagamento situacaoPagamento,
        UUID medicoUuid, LocalDate dataAtendimento, Pageable pageable);

Page<Consulta> findBySituacaoAndMedicoUuidAndDataAtendimento(SituacaoConsulta situacaoConsulta, UUID medicoUuid,
        LocalDate dataAtendimento, Pageable pageable);

Page<Consulta> findByPagamentoConsulta_SituacaoAndSituacaoAndMedicoUuidAndDataAtendimento(
        SituacaoPagamento situacaoPagamento, SituacaoConsulta situacaoConsulta, UUID medicoUuid,
        LocalDate dataAtendimento, Pageable pageable);
}
