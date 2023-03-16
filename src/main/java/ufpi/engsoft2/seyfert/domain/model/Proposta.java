package ufpi.engsoft2.seyfert.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private LocalTime horaFinal;
    private BigDecimal valor;
    private LocalDate dataAtendimento;

    @Enumerated(EnumType.STRING)
    private SituacaoProposta situacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitacao_id", insertable = false, updatable = false)
    private Solicitacao solicitacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", insertable = false, updatable = false)
    private Medico medico;

}
