package ufpi.engsoft2.seyfert.domain.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.DiaSemana;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HorarioDisponivelDTO {
    private DiaSemana diaDaSemana;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
}
