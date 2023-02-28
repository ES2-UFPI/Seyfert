package ufpi.engsoft2.seyfert.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ufpi.engsoft2.seyfert.domain.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    Paciente findByUuid(UUID uuidPaciente);
}
