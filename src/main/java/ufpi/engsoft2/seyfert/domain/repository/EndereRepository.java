package ufpi.engsoft2.seyfert.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpi.engsoft2.seyfert.domain.model.Endereco;

@Repository
public interface EndereRepository extends JpaRepository<Endereco, Long>{
    
}
