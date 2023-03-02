package ufpi.engsoft2.seyfert.service.consulta;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.domain.dto.ResponsePadraoParaAtualizacaoRecursoDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoConsulta;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoPagamento;
import ufpi.engsoft2.seyfert.domain.form.HorarioDisponivelMedicoForm;

public interface ConsultaService {
    ConsultaDTO getConsulta(UUID uuid);
    Page<ConsultaDTO> listarConsultasPaciente(UUID pacienteUiud, SituacaoConsulta situacaoConsulta, SituacaoPagamento situacaoPagamento, LocalDate dataAtendimento, Pageable pageable);
    Page<ConsultaDTO> listarConsultasMedico(UUID medicoUuid, SituacaoConsulta situacaoConsulta, SituacaoPagamento situacaoPagamento, LocalDate dataAtendimento, Pageable pageable);
    ResponsePadraoParaAtualizacaoRecursoDTO validarConsulta(UUID consultaUuid, String codigoValidacao);
    ResponsePadraoParaAtualizacaoRecursoDTO adicionarDetalhes(UUID consultaUuid, String detalhes);
    ResponsePadraoParaAtualizacaoRecursoDTO cadastrarHorarioDisponivel(UUID uuidMedico,
            HorarioDisponivelMedicoForm horario);
}
