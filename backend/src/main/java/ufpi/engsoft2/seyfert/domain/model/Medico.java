package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.Sexo;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoMedico;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_medico")
@Entity
public class Medico extends EntityBase {
    
    private String nome;
    private String sobrenome;
    private String nomeCompleto;
    private String descricao;
    private LocalDate dataNascimento;
    private String crm;

    @Enumerated(EnumType.STRING)
    private SituacaoMedico situacaoMedico;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    //Adicionar Especialidade
    //Avaliação Medica
    //Adicionar listar de endereços
}
