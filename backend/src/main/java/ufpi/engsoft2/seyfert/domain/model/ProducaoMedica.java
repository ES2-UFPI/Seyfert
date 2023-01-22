package ufpi.engsoft2.seyfert.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoProducaoMedica;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_producao_medica")
@Entity
public class ProducaoMedica extends EntityBase {
    private LocalDate competencia;
    private BigDecimal valorFaturado;
    private BigDecimal valorCancelado;
    private SituacaoProducaoMedica situacao;
}
