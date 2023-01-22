package ufpi.engsoft2.seyfert.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @PrePersist
    private void gerarCodigoConsulta(){
        String codigoVerificaoGerado = GeradorCodigoValidacaoConsulta.gerarCodigo();
        setCodigoVerificacao(codigoVerificaoGerado);
    }

    //Deve ser ligada com uma proposta e uma consulta
}
