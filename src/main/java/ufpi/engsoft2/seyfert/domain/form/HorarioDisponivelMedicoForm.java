package ufpi.engsoft2.seyfert.domain.form;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.DiaSemana;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HorarioDisponivelMedicoForm {
    private DiaSemana diaDaSemana;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
}
