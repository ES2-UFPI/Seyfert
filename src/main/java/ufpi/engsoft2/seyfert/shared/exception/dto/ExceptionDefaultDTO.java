package ufpi.engsoft2.seyfert.shared.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExceptionDefaultDTO {
    private String message;
    private Integer status;
    private String path;
}
