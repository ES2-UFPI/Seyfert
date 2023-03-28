package ufpi.engsoft2.seyfert.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ufpi.engsoft2.seyfert.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);    
}
