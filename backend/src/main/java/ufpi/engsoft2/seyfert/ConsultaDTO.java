//Classe temporária para testes

package ufpi.engsoft2.seyfert;

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
    public LocalDate dataAtendimento;
    public LocalTime horario;
    public BigDecimal valorConsulta;
    public String descricaoMedica;
    public String nomeCompletoMedico;
    public String nomeCompletoPaciente;

    @Enumerated(EnumType.STRING)
    public SituacaoConsulta situacaoConsulta;
    @Enumerated(EnumType.STRING)
    public SituacaoPagamento situacaoPagamento;
}
