package main.java.ufpi.engsoft2.seyfert.domain.form;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.Sexo;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropostaForm {
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
