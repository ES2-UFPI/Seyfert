package ufpi.engsoft2.seyfert.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoConsulta;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoPagamento;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConsultaDTO {
    private LocalDate dataAtendimento;
    private LocalTime horario;
    private BigDecimal valorConsulta;
    private String descricaoMedica;
    private String nomeCompletoMedico;
    private String nomeCompletoPaciente;

    @Enumerated(EnumType.STRING)
    private SituacaoConsulta situacaoConsulta;
    @Enumerated(EnumType.STRING)
    private SituacaoPagamento situacaoPagamento;
}
