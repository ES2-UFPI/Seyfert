package ufpi.engsoft2.seyfert.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoContato {
    EMAIL("Contato de email"),
    CELULAR("Contato de celular"),
    TELEFONE("Contato de telefone");

    private String descricao;
}
