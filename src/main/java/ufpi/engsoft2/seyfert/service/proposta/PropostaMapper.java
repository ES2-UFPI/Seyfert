package ufpi.engsoft2.seyfert.service.proposta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ufpi.engsoft2.seyfert.domain.dto.PropostaDTO;
import ufpi.engsoft2.seyfert.domain.form.PropostaForm;
import ufpi.engsoft2.seyfert.domain.model.Proposta;
import ufpi.engsoft2.seyfert.shared.mapper.EntityMapper;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface PropostaMapper extends EntityMapper<PropostaDTO, Proposta, PropostaForm>{
    
    @Mappings({
        @Mapping(target = "nomeCompletoMedico", expression = "java(entity.getMedico().getNomeCompleto())"),
        @Mapping(target = "medicoId", source = "medico.uuid"),
        @Mapping(target = "solicitacaoUuid", source = "solicitacao.uuid")
    })
    PropostaDTO toDto(Proposta entity);
}
