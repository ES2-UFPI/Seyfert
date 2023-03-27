package ufpi.engsoft2.seyfert.domain.model;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoMedico;
import ufpi.engsoft2.seyfert.domain.enums.TipoEndereco;
import ufpi.engsoft2.seyfert.shared.exception.BussinesRuleException;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_medico")
@Entity
public class Medico extends Usuario {

    private String descricao;
    private String crm;

    @Enumerated(EnumType.STRING)
    private SituacaoMedico situacaoMedico;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medico_id")
    private List<Contato> contatos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medico_id")
    private List<Endereco> enderecos;

    @OneToOne(mappedBy = "medico")
    private Avaliacao avaliacaoMedica;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medico_id")
    private List<Proposta> propostasFeitas;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medico_id")
    private List<HorarioDisponivelMedico> horariosDisponiveis;

    @ManyToAny(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_medico_especialidade", 
    joinColumns = {@JoinColumn(foreignKey = @ForeignKey)}, 
    inverseJoinColumns = {@JoinColumn(foreignKey = @ForeignKey)})
    private List<EspecialidadeMedica> especialidades;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medico_id")
    private List<ProducaoMedica> producoesMedicas;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medico_id")
    private List<Consulta> consultas;

    @PrePersist
    public void gerarNomeCompleto(){
        this.nomeCompleto = this.nome+" "+this.sobrenome;
    }

    public Endereco getEnderecoAtendimento(){
        for(Endereco endereco : enderecos){
            if(endereco.getTipoEndereco().equals(TipoEndereco.COMERCIAL)){
                return endereco;
            }
        }

        throw new BussinesRuleException("Médico sem endereço de atendimento");
    }
}
