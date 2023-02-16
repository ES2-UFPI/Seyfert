package ufpi.engsoft2.seyfert.service.consulta;

import java.util.UUID;

import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.domain.dto.ResponsePadraoParaAtualizacaoRecursoDTO;

public interface ConsultaService {
    ConsultaDTO getConsulta(UUID uuid);
    ResponsePadraoParaAtualizacaoRecursoDTO validarConsulta(UUID consultaUuid, String codigoValidacao);
}
