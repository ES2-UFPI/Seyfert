package ufpi.engsoft2.seyfert.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoEndereco {
    RESIDENCIAL("Endereço da casa"),
    COMERCIAL("Endereço de atendimento do médico");

    private String descricao;
}
