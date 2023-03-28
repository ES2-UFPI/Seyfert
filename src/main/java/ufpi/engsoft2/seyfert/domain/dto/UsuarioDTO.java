package ufpi.engsoft2.seyfert.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.Sexo;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioDTO {
    private UUID uuid;
    private String nome;
    private String sobrenome;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;
}
