package ufpi.engsoft2.seyfert.service.proposta.impl;

import java.time.LocalDate;
import java.util.UUID;

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
    public Page<PropostaDTO> listarPropostasCliente(SituacaoProposta situacao, UUID solicitacao, UUID medico, LocalDate dataAtendimento, Pageable paginacao) {
        Page<Proposta> propostasPage;
       
        SituacaoProposta situacaoProposta = situacao == null ? SituacaoProposta.CRIADA : situacao;
        if(dataAtendimento != null){
            if(solicitacao != null){
                propostasPage = propostaRepository.findBySolicitacaoUuidAndDataAtendimento(solicitacao, dataAtendimento, paginacao);
            }else if(medico != null){
                propostasPage = propostaRepository.findByMedicoUuidAndDataAtendimento(medico, dataAtendimento, paginacao);
            }else {
                propostasPage = propostaRepository.findByDataAtendimento(dataAtendimento, paginacao);
            }
            return propostasPage.map(this.propostaServiceMapper::toDto);
        } else {            
            if(solicitacao != null && medico == null){
                propostasPage = propostaRepository.findBySolicitacaoUuidAndSituacao(solicitacao, situacao, paginacao);
            }
            
            if(medico != null && solicitacao == null){
                propostasPage = propostaRepository.findByMedicoUuidAndSituacao(medico, situacao, paginacao);
            }
        }

        propostasPage = propostaRepository.findBySituacao(situacaoProposta, paginacao);
        
        return propostasPage.map(this.propostaServiceMapper::toDto);

    }
    
}
