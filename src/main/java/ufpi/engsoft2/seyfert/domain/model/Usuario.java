package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.Sexo;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_usuario")
@Entity
public class Usuario extends EntityBase {
    
    protected String nome;
    protected String sobrenome;
    protected String nomeCompleto;
    private LocalDate dataNascimento;
    private String cpf;
    
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String senha;
    
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
}
