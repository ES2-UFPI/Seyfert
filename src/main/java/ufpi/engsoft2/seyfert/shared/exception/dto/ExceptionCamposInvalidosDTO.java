package ufpi.engsoft2.seyfert.shared.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionCamposInvalidosDTO {
    private String campo;
    private String mensagem;
}
