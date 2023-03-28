package ufpi.engsoft2.seyfert.service.medico;

import org.springframework.stereotype.Service;

import ufpi.engsoft2.seyfert.domain.model.Medico;

@Service
public class MedicoServiceImpl {
    
    public Medico salvar(Medico medico){
        medico.setId(1L);

        return medico;
    }
}
