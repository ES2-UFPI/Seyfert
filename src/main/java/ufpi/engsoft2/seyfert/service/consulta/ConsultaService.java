package ufpi.engsoft2.seyfert.service.consulta;

import java.util.UUID;

import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;

public interface ConsultaService {
    ConsultaDTO getConsulta(UUID uuid);
}