package ufpi.engsoft2.seyfert.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_paciente")
@Entity
public class Paciente extends Usuario {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "paciente_id")
    private List<Contato> contatos;

    @OneToOne(mappedBy = "paciente", fetch = FetchType.EAGER)
    private Endereco endereco;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "comentador_id")
    private List<ComentarioAvaliacao> comentariosEmAvaliacaoes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "paciente_id")
    private List<Solicitacao> solicitacaes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "paciente_id")
    private List<Conversa> conversas;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "paciente_id")
    private List<PagamentoConsulta> pagamentos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "paciente_id")
    private List<Consulta> consultas;

    @PrePersist
    private void gerarNomeCompleto(){
        this.nomeCompleto = this.nome+" "+this.sobrenome;
    }
}
