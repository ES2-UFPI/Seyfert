package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "auditor_medico_id")
    private List<Conversa> conversas;

    @PrePersist
    private void gerarNomeCompleto(){
        this.nomeCompleto = this.nome+" "+this.sobrenome;
    }


}
