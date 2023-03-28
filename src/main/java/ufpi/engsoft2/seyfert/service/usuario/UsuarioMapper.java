package ufpi.engsoft2.seyfert.service.usuario;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import ufpi.engsoft2.seyfert.domain.dto.UsuarioDTO;
import ufpi.engsoft2.seyfert.domain.model.Usuario;
import ufpi.engsoft2.seyfert.shared.mapper.EntityNoFormMapper;

@Component
@Mapper(
    componentModel = "spring", 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UsuarioMapper extends EntityNoFormMapper<UsuarioDTO, Usuario>{
    UsuarioDTO toDto(Usuario usuario);
}
