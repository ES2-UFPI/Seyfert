package ufpi.engsoft2.seyfert.service.usuario.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.dto.UsuarioDTO;
import ufpi.engsoft2.seyfert.domain.model.Usuario;
import ufpi.engsoft2.seyfert.domain.repository.UsuarioRepository;
import ufpi.engsoft2.seyfert.service.usuario.UsuarioMapper;
import ufpi.engsoft2.seyfert.service.usuario.UsuarioService;
import ufpi.engsoft2.seyfert.shared.exception.EntityNotFoundException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioDTO getUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        return usuarioMapper.toDto(usuario);
    }
    
}
