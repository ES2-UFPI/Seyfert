package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.Sexo;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoSolicitacao;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_solicitacao")
@Entity
public class Solicitacao extends EntityBase {
    private LocalDate dataParaAtendimento;
    private LocalTime horaInicial;
    private LocalTime horaFinal;

    private String descricaoSolicitacao;

    @Enumerated(EnumType.STRING)
    private SituacaoSolicitacao situacaoSolicitacao;
    
    @Enumerated(EnumType.STRING)
    private Sexo sexoPreferivelDoAtendimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", insertable = false, updatable = false)
    private Paciente paciente;

    @OneToOne(mappedBy = "solicitacao")
    private Consulta consulta;

    @OneToOne
    private EspecialidadeMedica especialidadeMedica;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "solicitacao_id")
    private List<Proposta> propostas = new ArrayList();

    // private EspecialidadeMedica especialidadeMedica;
    //Deve está relacionada com uma consulta, caso seja gerada
}
