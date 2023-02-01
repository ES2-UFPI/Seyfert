package ufpi.engsoft2.seyfert.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.model.EspecialidadeMedica;

@Repository
public interface EspecialidadeMedicaRepository extends JpaRepository<EspecialidadeMedica, Long> {
    EspecialidadeMedica findByNomeEspecialidade(String nomeEspecialidade);
}
