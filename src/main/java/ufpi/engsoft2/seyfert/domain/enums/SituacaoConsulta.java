package ufpi.engsoft2.seyfert.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SituacaoConsulta {
    AGUARDANDO_ATENDIMENTO("Quando a consulta foi gerada, e agora só falta ser atendida"),
    FINALIZADA("Quando foi atendida e finalizada"),
    CANCELADA("Quando médico cancela uma consulta"),
    VENCIDA("Quando passa da data de atendimento e não é finalizada"),
    ESTORNADA("Quando o paciente recebe o dinheiro de volta da consulta concelada");

    private String descricao;
}
