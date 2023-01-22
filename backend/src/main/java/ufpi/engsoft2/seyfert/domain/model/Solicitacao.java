package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private SituacaoSolicitacao situacaoSolicitacao;
    
    @Enumerated(EnumType.STRING)
    private Sexo sexoPreferivelDoAtendimento;
    // private EspecialidadeMedica especialidadeMedica;
    //Deve está relacionada com uma consulta, caso seja gerada
}
