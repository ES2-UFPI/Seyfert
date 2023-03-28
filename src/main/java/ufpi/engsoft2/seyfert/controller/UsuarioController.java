package ufpi.engsoft2.seyfert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufpi.engsoft2.seyfert.domain.dto.UsuarioDTO;
import ufpi.engsoft2.seyfert.service.usuario.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<UsuarioDTO> getUsuario(@RequestParam String email, @RequestParam String senha) {
        return ResponseEntity.ok(usuarioService.getUsuario(email));
    }
}
