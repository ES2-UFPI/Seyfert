package ufpi.engsoft2.seyfert.service.proposta.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ufpi.engsoft2.seyfert.domain.dto.PropostaDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoProposta;
import ufpi.engsoft2.seyfert.domain.model.Proposta;
import ufpi.engsoft2.seyfert.domain.repository.PropostaRepository;
import ufpi.engsoft2.seyfert.service.proposta.PropostaService;
import ufpi.engsoft2.seyfert.service.proposta.PropostaServiceMapper;

@Service
public class PropostaServiceImpl implements PropostaService{

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private PropostaServiceMapper propostaServiceMapper;

    @Override
    public Page<PropostaDTO> listarPropostasCliente(Pageable paginacao) {
       Page<Proposta> propostasPage = propostaRepository.findBySituacao(SituacaoProposta.CRIADA, paginacao);
        return propostasPage.map(this.propostaServiceMapper::toDto);
    }
    
}
