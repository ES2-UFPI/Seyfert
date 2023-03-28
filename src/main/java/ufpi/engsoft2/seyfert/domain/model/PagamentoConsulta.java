package ufpi.engsoft2.seyfert.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.FormaPagamento;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoPagamento;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_pagamento_consulta")
@Entity
public class PagamentoConsulta extends EntityBase {
    
    private BigDecimal valorPago;
    private LocalDateTime dataPagamento;
    private LocalDateTime vencimento;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private SituacaoPagamento situacao;

    @OneToOne
    private Consulta consulta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", insertable = false, updatable = false)
    private Paciente paciente;
}
