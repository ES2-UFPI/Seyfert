package ufpi.engsoft2.seyfert.domain.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoConsulta;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaForm {
    
    private UUID medicoUuid;
    private UUID pacienteUuid;
    private LocalDate dataAtendimento;
    private LocalTime horario;
    private BigDecimal valorConsulta;
    private String descricaoMedica;
    private String codigoVerificacao;

    @Enumerated(EnumType.STRING)
    private SituacaoConsulta situacaoConsulta;
    
    private BigDecimal valorPago;
    private LocalDateTime vencimento;
}
