package ufpi.engsoft2.seyfert.service.consulta.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityNotFoundException;
import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.domain.model.Consulta;
import ufpi.engsoft2.seyfert.domain.repository.ConsultaRepository;
import ufpi.engsoft2.seyfert.service.consulta.ConsultaMapper;
import ufpi.engsoft2.seyfert.service.consulta.ConsultaService;
import ufpi.engsoft2.seyfert.shared.exception.EntityNotFound;

public class ConsultaServiceImpl implements ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    ConsultaMapper consultaMapper;

    public ConsultaDTO getConsulta(UUID uuid) {
        Consulta consulta = consultaRepository.findByUuid(uuid);
        if (consulta == null) {
            throw new EntityNotFound("Consulta não encontrada");
        }

        return consultaMapper.toDto(consulta);
    }
}
