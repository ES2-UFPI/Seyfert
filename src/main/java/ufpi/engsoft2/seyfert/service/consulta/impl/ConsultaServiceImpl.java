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
import ufpi.engsoft2.seyfert.shared.exception.EntityNotFoundException;

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
            throw new EntityNotFoundException("Consulta não encontrada");
        }

        return consultaMapper.toDto(consulta);
    }

    public void cadastrarHorarioDisponivel(UUID medicoUuid, HorarioDisponivelMedico horarioDisponivel){
        Medico medico = medicoRepository.findById(medicoUuid);

        List<HorarioDisponivelMedico> horarios = medico.getHorariosDisponiveis();

        for (HorarioDisponivelMedico horario : horarios) {
            if (horario.equals(horarioDisponivel)) {
                throw new BussinesRuleException("Horario já cadastrado");
            }
        }

        horarios.add(horarioDisponivel);

        medico.setHorariosDisponiveis(horarios);

        medicoRepository.save(medico);
    }
}
