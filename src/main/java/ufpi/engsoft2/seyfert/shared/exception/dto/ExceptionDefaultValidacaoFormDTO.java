package ufpi.engsoft2.seyfert.shared.exception.dto;

import java.util.List;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class ExceptionDefaultValidacaoFormDTO extends ExceptionDefaultDTO {
    private List<ExceptionCamposInvalidosDTO> campos;

    public ExceptionDefaultValidacaoFormDTO(String mensagem, Integer status, String path, List<ExceptionCamposInvalidosDTO> camposInvalidos){
        super(mensagem, status, path);
        this.campos = camposInvalidos;
    }

}
