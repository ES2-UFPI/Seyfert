package ufpi.engsoft2.seyfert.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponsePadraoParaAtualizacaoRecursoDTO {
    private String mensagem;
}
