package ufpi.engsoft2.seyfert.domain.model;

import java.math.BigDecimal;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoProposta;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_proposta")
@Entity
public class Proposta extends EntityBase {
    
    private LocalTime horaInicial;
    private LocalTime horafinal;
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private SituacaoProposta situacao;
}
