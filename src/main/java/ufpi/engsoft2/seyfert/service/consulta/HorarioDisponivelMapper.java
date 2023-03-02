package ufpi.engsoft2.seyfert.service.consulta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.domain.form.HorarioDisponivelMedicoForm;
import ufpi.engsoft2.seyfert.domain.model.Consulta;
import ufpi.engsoft2.seyfert.shared.mapper.EntityNoFormMapper;

@Component
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface HorarioDisponivelMedico extends EntityNoDTOMapper<HorarioDisponivelMedicoForm> {
    
}
