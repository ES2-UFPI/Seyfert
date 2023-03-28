package ufpi.engsoft2.seyfert.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tb_especialidade_medica")
@Entity
public class EspecialidadeMedica extends EntityBase {
    private String nomeEspecialidade;
    private String descricao;
    private Integer tempoMedioConsultaEmMinutos;

    @ManyToMany(mappedBy = "especialidades", cascade = CascadeType.ALL)
    private List<Medico> medicos; 
}
