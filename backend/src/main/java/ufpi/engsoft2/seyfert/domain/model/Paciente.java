package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.Sexo;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_paciente")
@Entity
public class Paciente extends EntityBase {

    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    private String nomeCompleto;
    
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @PrePersist
    private void gerarNomeCompleto(){
        this.nomeCompleto = this.nome+" "+this.sobrenome;
    }
}
