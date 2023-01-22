package ufpi.engsoft2.seyfert.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_mensagem_conversa")
@Entity
public class MensagemConversa extends EntityBase {
    private String mensagem;
    private LocalDateTime horarioEnvio;
    private UUID remetente;
    private UUID emisso;
}
