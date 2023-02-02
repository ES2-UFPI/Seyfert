package ufpi.engsoft2.seyfert.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ufpi.engsoft2.seyfert.domain.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, UUID>{
    Medico findByUuid(UUID uuidMedico);
}
