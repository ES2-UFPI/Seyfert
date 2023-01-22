package ufpi.engsoft2.seyfert.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoConsulta;
import ufpi.engsoft2.seyfert.shared.utils.GeradorCodigoValidacaoConsulta;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_consulta")
@Entity
public class Consulta extends EntityBase {
    
    private LocalDate dataAtendimento;
    private LocalTime horario;
    private BigDecimal valorConsulta;
    private String descricaoMedica;
    private String codigoVerificacao;

    @Enumerated(EnumType.STRING)
    private SituacaoConsulta situacao;

    @OneToOne
    private Solicitacao solicitacao;

    @OneToOne(mappedBy = "consulta")
    private PagamentoConsulta pagamentoConsulta;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @PrePersist
    private void gerarCodigoConsulta(){
        String codigoVerificaoGerado = GeradorCodigoValidacaoConsulta.gerarCodigo();
        setCodigoVerificacao(codigoVerificaoGerado);
    }
}
