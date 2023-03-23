package ufpi.engsoft2.seyfert.especialidade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class EspecialidadeServiceTest {

    @Test
    @DisplayName("Esse é uma teste inicial")
    public void verSeTesteEstaFuncionando(){
        Boolean testeVerdadeiro = false;

        assertEquals(true, testeVerdadeiro);
    }
    
}
