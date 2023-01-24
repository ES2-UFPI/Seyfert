package ufpi.engsoft2.seyfert.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    private Medico medico;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "avaliacao_id")
    private List<ComentarioAvaliacao> comentarios;
} 
