package ufpi.engsoft2.seyfert.solicitacao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
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

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.Sexo;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoSolicitacao;
import ufpi.engsoft2.seyfert.domain.model.EspecialidadeMedica;
import ufpi.engsoft2.seyfert.domain.model.Paciente;
import ufpi.engsoft2.seyfert.domain.model.Solicitacao;
import ufpi.engsoft2.seyfert.domain.repository.EspecialidadeMedicaRepository;
import ufpi.engsoft2.seyfert.domain.repository.PacienteRepository;
import ufpi.engsoft2.seyfert.domain.repository.SolicitacaoRepository;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@SuppressWarnings("unused")
@RequiredArgsConstructor
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class SolicitacaoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EspecialidadeMedicaRepository especialidadeMedicaRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @BeforeAll
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

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setDataParaAtendimento(LocalDate.now().plusYears(1));
        solicitacao.setHoraInicial(LocalTime.now().plusHours(1));
        solicitacao.setHoraFinal(LocalTime.now().plusHours(2));
        solicitacao.setDescricaoSolicitacao("Teste de solicitacao");
        solicitacao.setSexoPreferivelDoAtendimento(Sexo.OUTROS);
        solicitacao.setPaciente(paciente);
        solicitacao.setEspecialidadeMedica(especialidadeMedica);
        solicitacaoRepository.save(solicitacao);

        solicitacao = new Solicitacao();
        solicitacao.setDataParaAtendimento(LocalDate.now().plusYears(1));
        solicitacao.setHoraInicial(LocalTime.now().plusHours(1));
        solicitacao.setHoraFinal(LocalTime.now().plusHours(2));
        solicitacao.setDescricaoSolicitacao("Teste de solicitacao");
        solicitacao.setSexoPreferivelDoAtendimento(Sexo.OUTROS);
        solicitacao.setPaciente(paciente);
        solicitacao.setEspecialidadeMedica(especialidadeMedica);
        solicitacaoRepository.save(solicitacao);
    }

    @AfterAll
    public void tearDown() {
        solicitacaoRepository.deleteAll();
        especialidadeMedicaRepository.deleteAll();
        pacienteRepository.deleteAll();
    }
    
    @Test
    @DisplayName("Teste de cadastro de solicitacoes endpoint not found")
    public void cadastroDeSolicitacaoEndpointNotFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/solicitacao", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"dataParaAtendimento\": \"2023-01-25\", \"horaInicial\": \"10:00\", \"horaFinal\": \"11:00\", \"descricaoSolicitacao\": \"Teste de solicitacao\", \"sexoPreferivelDoAtendimento\": \"OUTROS\", \"pacienteUuid\": \"f9b360de-d79a-4770-9c15-a62591e67922\", \"especialidadeMedicaUuid\": \"8e08cfed-0960-47dd-afe1-d1e5017a874b\"}"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
