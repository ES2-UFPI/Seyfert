package ufpi.engsoft2.seyfert.solicitacao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ufpi.engsoft2.seyfert.domain.dto.SolicitacaoDTO;
import ufpi.engsoft2.seyfert.domain.enums.Sexo;
import ufpi.engsoft2.seyfert.domain.form.SolicitacaoForm;
import ufpi.engsoft2.seyfert.domain.model.EspecialidadeMedica;
import ufpi.engsoft2.seyfert.domain.model.Paciente;
import ufpi.engsoft2.seyfert.domain.repository.EspecialidadeMedicaRepository;
import ufpi.engsoft2.seyfert.domain.repository.PacienteRepository;
import ufpi.engsoft2.seyfert.domain.repository.SolicitacaoRepository;
import ufpi.engsoft2.seyfert.service.solicitacao.impl.SolicitacaoServiceImpl;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@SuppressWarnings("unused")
@RequiredArgsConstructor
@TestInstance(Lifecycle.PER_CLASS)
public class SolicitacaoServiceTest {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EspecialidadeMedicaRepository especialidadeMedicaRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private SolicitacaoServiceImpl solicitacaoService;

    @BeforeEach
    public void setUp() {
        Paciente paciente = new Paciente();
        paciente.setNome("Teste");
        paciente.setSobrenome("Teste");
        paciente.setDataNascimento(LocalDate.now().minusYears(20));
        paciente.setCpf("12345678910");
        paciente.setSexo(Sexo.MASCULINO);
        paciente.setUuid(UUID.fromString("f9b360de-d79a-4770-9c15-a62591e67022"));
        pacienteRepository.save(paciente);

        EspecialidadeMedica especialidadeMedica = new EspecialidadeMedica();
        especialidadeMedica.setNomeEspecialidade("Cardiologia");
        especialidadeMedica.setDescricao("Especialidade que trata de doenças do coração");
        especialidadeMedica.setTempoMedioConsultaEmMinutos(30);
        especialidadeMedica.setUuid(UUID.fromString("8e08cfed-0960-47dd-afe1-d1e5017d874b"));
        especialidadeMedicaRepository.save(especialidadeMedica);
    }

    @Test
    @DisplayName("Deve ser bem sucedido ao cadastrar uma solicitação")
    public void cadastrarSolicitacao() throws Exception {
        SolicitacaoForm solicitacaoForm = new SolicitacaoForm();
        solicitacaoForm.setHoraInicial(LocalTime.now().plusHours(1));
        solicitacaoForm.setHoraFinal(LocalTime.now().plusHours(2));
        solicitacaoForm.setDataParaAtendimento(LocalDate.now());
        solicitacaoForm.setDescricaoSolicitacao("Teste");
        solicitacaoForm.setSexoPreferivelDoAtendimento(Sexo.MASCULINO);
        solicitacaoForm.setPacienteUuid(UUID.fromString("f9b360de-d79a-4770-9c15-a62591e67022"));
        solicitacaoForm.setEspecialidadeMedicaUuid(UUID.fromString("8e08cfed-0960-47dd-afe1-d1e5017d874b"));

        SolicitacaoDTO resposta = solicitacaoService.cadastrar(solicitacaoForm);

        assertEquals(resposta.getHoraInicial(), solicitacaoForm.getHoraInicial());
        assertEquals(resposta.getHoraFinal(), solicitacaoForm.getHoraFinal());
        assertEquals(resposta.getDataParaAtendimento(), solicitacaoForm.getDataParaAtendimento());
        assertEquals(resposta.getDescricaoSolicitacao(), solicitacaoForm.getDescricaoSolicitacao());
        assertEquals(resposta.getSexoPreferivelDoAtendimento(), solicitacaoForm.getSexoPreferivelDoAtendimento());
    }
}
