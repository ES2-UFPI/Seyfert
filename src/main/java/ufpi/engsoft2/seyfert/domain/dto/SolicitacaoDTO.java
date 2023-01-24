package ufpi.engsoft2.seyfert.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.Sexo;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoSolicitacao;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SolicitacaoDTO {
    private UUID uuid;
    private LocalDate dataParaAtendimento;
    private LocalTime horaInicial;
    private LocalTime horaFinal;
    private String descricaoSolicitacao;
    private String nomePaciente;
    private String nomeEspecialidade;
    
    @Enumerated(EnumType.STRING)
    private Sexo sexoPreferivelDoAtendimento;
    @Enumerated(EnumType.STRING)
    private SituacaoSolicitacao situacaoSolicitacao;
}
