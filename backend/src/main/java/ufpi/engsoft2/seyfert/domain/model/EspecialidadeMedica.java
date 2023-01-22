package ufpi.engsoft2.seyfert.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_especialidade_medica")
@Entity
public class EspecialidadeMedica extends EntityBase{
    private String nomeEspecialidade;
    private String descricao;
    private Integer tempoMedioConsultaEmMinutos;
}
