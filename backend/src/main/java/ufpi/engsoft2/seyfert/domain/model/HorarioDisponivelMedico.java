package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.DiaSemana;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_horario_disponivel_medico")
@Entity
public class HorarioDisponivelMedico extends EntityBase {
    
    private DiaSemana diaDaSemana;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", insertable = false, updatable = false)
    private Medico medico;
}
