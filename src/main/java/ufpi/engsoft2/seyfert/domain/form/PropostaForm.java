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
public class PropostaForm {
    private LocalDate dataAtendimento;
    private LocalTime horaInicial;
    private LocalTime horaFinal;
    private BigDecimal valor;
}
