package ufpi.engsoft2.seyfert.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    Consulta findByUuid(UUID uuid);
}
