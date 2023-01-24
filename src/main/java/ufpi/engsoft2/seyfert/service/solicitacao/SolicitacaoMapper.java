package ufpi.engsoft2.seyfert.service.solicitacao;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import ufpi.engsoft2.seyfert.domain.dto.SolicitacaoDTO;
import ufpi.engsoft2.seyfert.domain.form.SolicitacaoForm;
import ufpi.engsoft2.seyfert.domain.model.Solicitacao;
import ufpi.engsoft2.seyfert.shared.mapper.EntityMapper;

@Component
@Mapper(
    componentModel = "spring", 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SolicitacaoMapper extends EntityMapper<SolicitacaoDTO, Solicitacao, SolicitacaoForm>{
    @Mappings({
        @Mapping(target = "nomePaciente", source = "paciente.nome"),
        @Mapping(target = "nomeEspecialidade", source = "especialidadeMedica.nomeEspecialidade")
    })
    SolicitacaoDTO toDto(Solicitacao solicitacao);
}
