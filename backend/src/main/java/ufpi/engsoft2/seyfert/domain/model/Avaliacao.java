package ufpi.engsoft2.seyfert.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_avaliacao")
@Entity
public class Avaliacao extends EntityBase {
    
    private Integer votosPositivos;
    private Integer votoNegativos;

}
