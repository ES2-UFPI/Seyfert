package ufpi.engsoft2.seyfert.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.TipoContato;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_contato")
@Entity
public class Contato extends EntityBase {

    private String contato;

    @Enumerated(EnumType.STRING)
    private TipoContato tipoContato;   
    
    private Boolean ativo;
}
