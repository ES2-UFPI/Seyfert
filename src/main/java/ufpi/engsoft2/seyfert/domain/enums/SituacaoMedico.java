package ufpi.engsoft2.seyfert.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SituacaoMedico {
    CADASTRADO("Quando médico se cadastra"),
    ATIVO("Quando médico for auditado"),
    BANIDO("Quando médico é banido"),
    SUSPENSO("Quando médico está suspenso");

    private String descricao;
}
