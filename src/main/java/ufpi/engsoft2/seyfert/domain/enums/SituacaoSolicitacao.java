package ufpi.engsoft2.seyfert.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SituacaoSolicitacao {
    SOLICITADA("Quando é criada"),
    COM_PROPOSTA("Quando recebe uma proposta"),
    CANCELADA("Quando é cancelada pelo paciente"),
    CONFIRMADA("Quando possui uma confirmação de proposta");

    private String descricao;
}
