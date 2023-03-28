package ufpi.engsoft2.seyfert.routines;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ufpi.engsoft2.seyfert.shared.database.InsertsDefault;

@RequiredArgsConstructor
@Component
public class PopularBanco implements ApplicationRunner {

    private final InsertsDefault insertsDefault;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            insertsDefault.popularBanco();
        } catch (Exception e) {
            System.out.println("Erro ao executar rotina"+e.getMessage());
        }
        
    }
    
}