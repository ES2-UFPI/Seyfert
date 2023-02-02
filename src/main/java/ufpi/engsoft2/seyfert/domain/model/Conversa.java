package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_conversa")
@Entity
public class Conversa extends EntityBase {

    private LocalDateTime dataInicioConversa;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "conversa_id")
    private List<MensagemConversa> mensagens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_medico_id", insertable = false, updatable = false)
    private AuditorMedico auditorMedico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", insertable = false, updatable = false)
    private Paciente paciente;
}
