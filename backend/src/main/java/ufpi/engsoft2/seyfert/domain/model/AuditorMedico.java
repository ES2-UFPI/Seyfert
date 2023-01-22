package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_auditor_medico")
@Entity
public class AuditorMedico extends EntityBase {
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;
    private String nomeCompleto;

    @PrePersist
    private void gerarNomeCompleto(){
        this.nomeCompleto = this.nome+" "+this.sobrenome;
    }
}
