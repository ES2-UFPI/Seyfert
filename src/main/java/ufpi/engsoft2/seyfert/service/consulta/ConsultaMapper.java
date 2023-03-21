package ufpi.engsoft2.seyfert.service.consulta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.domain.form.ConsultaForm;
import ufpi.engsoft2.seyfert.domain.model.Consulta;
import ufpi.engsoft2.seyfert.shared.mapper.EntityMapper;

@Component
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface ConsultaMapper extends EntityMapper<ConsultaDTO, Consulta, ConsultaForm> {
    @Mappings({
        @Mapping(target = "nomeCompletoPaciente", source = "paciente.nomeCompleto"),
        @Mapping(target = "nomeCompletoMedico", source = "medico.nomeCompleto"),
        @Mapping(target = "situacaoPagamento", source = "pagamentoConsulta.situacao"),
        @Mapping(target = "situacaoConsulta", source = "situacao")
    })
    ConsultaDTO toDto(Consulta consulta);
}
