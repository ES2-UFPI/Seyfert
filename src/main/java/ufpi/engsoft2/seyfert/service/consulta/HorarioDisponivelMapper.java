package ufpi.engsoft2.seyfert.service.consulta;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import ufpi.engsoft2.seyfert.domain.dto.HorarioDisponivelDTO;
import ufpi.engsoft2.seyfert.domain.form.HorarioDisponivelMedicoForm;
import ufpi.engsoft2.seyfert.domain.model.HorarioDisponivelMedico;
import ufpi.engsoft2.seyfert.shared.mapper.EntityMapper;

@Component
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface HorarioDisponivelMapper extends EntityMapper<HorarioDisponivelDTO, HorarioDisponivelMedico, HorarioDisponivelMedicoForm> {
    
}
