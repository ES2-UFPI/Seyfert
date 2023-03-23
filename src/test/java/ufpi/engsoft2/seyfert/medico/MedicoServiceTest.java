package ufpi.engsoft2.seyfert.medico;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import lombok.RequiredArgsConstructor;
import ufpi.engsoft2.seyfert.domain.model.Medico;
import ufpi.engsoft2.seyfert.service.medico.MedicoServiceImpl;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class MedicoServiceTest {

    private final MedicoServiceImpl medicoServiceImpl = new MedicoServiceImpl();
    
    @Test
    @DisplayName("Esse é uma teste inicial")
    public void verSeTesteEstaFuncionando(){
        Boolean testeVerdadeiro = false;
        Medico medico = new Medico();

        medico.setNome("Metheus");
        medico.setDataNascimento(LocalDate.now());

        medico = medicoServiceImpl.salvar(medico);

        assertEquals(1L, medico.getId());
    }
}
