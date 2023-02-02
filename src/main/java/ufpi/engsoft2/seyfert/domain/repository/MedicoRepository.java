<<<<<<< HEAD
package main.java.ufpi.engsoft2.seyfert.domain.repository;
=======
package ufpi.engsoft2.seyfert.domain.repository;
>>>>>>> 6fdd39907ac372e3afb15ff20cfe3f4871c4b3b9

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.model.Solicitacao;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
    Medico findByUuid(UUID uuid);
=======

import ufpi.engsoft2.seyfert.domain.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, UUID>{
    Medico findByUuid(UUID uuidMedico);
>>>>>>> 6fdd39907ac372e3afb15ff20cfe3f4871c4b3b9
}
