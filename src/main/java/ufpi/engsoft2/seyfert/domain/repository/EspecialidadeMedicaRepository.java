package ufpi.engsoft2.seyfert.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ufpi.engsoft2.seyfert.domain.model.EspecialidadeMedica;

public interface EspecialidadeMedicaRepository extends JpaRepository<EspecialidadeMedica, Long> {
    EspecialidadeMedica findByNomeEspecialidade(String nomeEspecialidade);
    EspecialidadeMedica findByUuid(UUID uuid);
}
