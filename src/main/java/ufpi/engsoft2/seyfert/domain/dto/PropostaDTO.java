package ufpi.engsoft2.seyfert.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoProposta;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PropostaDTO {
    private UUID uuid;
    private LocalTime horaInicial;
    private LocalTime horafinal;
    private LocalDate dataAtendimento;
    private BigDecimal valor;
    private UUID solicitacaoUuid;
    
    @Enumerated(EnumType.STRING)
    private SituacaoProposta situacao;

    private String nomeCompletoMedico;
    private UUID medicoId;
}
