package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.Sexo;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tb_usuario")
@Entity
public class Usuario extends EntityBase {
    
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String senha;
    
    @OneToOne
    private Medico medico;

    @OneToOne
    private Paciente paciente;
}
