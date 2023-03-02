package ufpi.engsoft2.seyfert.domain.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HorarioDisponivelMedicoForm {
    private DiaSemana diaDaSemana;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
}
