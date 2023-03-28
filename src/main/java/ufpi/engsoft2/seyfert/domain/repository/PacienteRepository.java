package ufpi.engsoft2.seyfert.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    Paciente findByUuid(UUID uuidPaciente);
}
