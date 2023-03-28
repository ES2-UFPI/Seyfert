package ufpi.engsoft2.seyfert.service.usuario;

import ufpi.engsoft2.seyfert.domain.dto.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO getUsuario(String email);
}
