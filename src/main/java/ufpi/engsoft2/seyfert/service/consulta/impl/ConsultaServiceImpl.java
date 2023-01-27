package ufpi.engsoft2.seyfert.service.consulta.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.domain.model.Consulta;
import ufpi.engsoft2.seyfert.domain.repository.ConsultaRepository;
import ufpi.engsoft2.seyfert.service.consulta.ConsultaMapper;
import ufpi.engsoft2.seyfert.service.consulta.ConsultaService;
import ufpi.engsoft2.seyfert.shared.exception.EntityNotFound;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaMapper consultaMapper;

    @Override
    public ConsultaDTO getConsulta(UUID uuid) {
        Consulta consulta = consultaRepository.findByUuid(uuid);
        if (consulta == null) {
            throw new EntityNotFound("Consulta não encontrada");
        }

        return consultaMapper.toDto(consulta);
    }
}
