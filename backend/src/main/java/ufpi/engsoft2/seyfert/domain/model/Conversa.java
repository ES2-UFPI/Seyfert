package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_conversa")
@Entity
public class Conversa extends EntityBase{
    private LocalDateTime dataInicioConversa;
}
