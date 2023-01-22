package ufpi.engsoft2.seyfert.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.TipoEndereco;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_endereco")
@Entity
public class Endereco extends EntityBase {
    private String cidade;
    private String bairro;
    private Integer numero;
    private String complemento;
    private String estado;
    private String cep;

    @Enumerated(EnumType.STRING)
    private TipoEndereco tipoEndereco;
}
