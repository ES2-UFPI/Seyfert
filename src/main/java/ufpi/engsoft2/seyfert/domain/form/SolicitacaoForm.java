package ufpi.engsoft2.seyfert.domain.form;

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
public class SolicitacaoForm {
    private LocalDate dataParaAtendimento;
    private LocalTime horaInicial;
    private LocalTime horaFinal;

    private String descricaoSolicitacao;
    
    private Sexo sexoPreferivelDoAtendimento;

    private UUID pacienteUuid;

    private UUID especialidadeMedicaUuid;
}
