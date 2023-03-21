package ufpi.engsoft2.seyfert.service.proposta.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ufpi.engsoft2.seyfert.domain.dto.PropostaDTO;
import ufpi.engsoft2.seyfert.domain.dto.ResponsePadraoParaAtualizacaoRecursoDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoProposta;
import ufpi.engsoft2.seyfert.domain.form.PropostaForm;
import ufpi.engsoft2.seyfert.domain.model.Medico;
import ufpi.engsoft2.seyfert.domain.model.Proposta;
import ufpi.engsoft2.seyfert.domain.model.Solicitacao;
import ufpi.engsoft2.seyfert.domain.repository.MedicoRepository;
import ufpi.engsoft2.seyfert.domain.repository.PropostaRepository;
import ufpi.engsoft2.seyfert.domain.repository.SolicitacaoRepository;
import ufpi.engsoft2.seyfert.service.proposta.PropostaService;
import ufpi.engsoft2.seyfert.service.proposta.PropostaMapper;
import ufpi.engsoft2.seyfert.shared.exception.BussinesRuleException;
import ufpi.engsoft2.seyfert.shared.exception.EntityNotFoundException;

@Service
public class PropostaServiceImpl implements PropostaService{

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private PropostaMapper propostaMapper;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private MedicoRepository medicoRepository;

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
            return propostasPage.map(this.propostaMapper::toDto);
        } else {            
            if(solicitacao != null && medico == null){
                propostasPage = propostaRepository.findBySolicitacaoUuidAndSituacao(solicitacao, situacao, paginacao);
            }
            
            if(medico != null && solicitacao == null){
                propostasPage = propostaRepository.findByMedicoUuidAndSituacao(medico, situacao, paginacao);
            }
        }

        propostasPage = propostaRepository.findBySituacao(situacaoProposta, paginacao);
        
        return propostasPage.map(this.propostaMapper::toDto);
    }

    @Override
    public ResponsePadraoParaAtualizacaoRecursoDTO adicionarPropostas(UUID solicitacaoUuid, UUID medicoUuid, List<PropostaForm> propostas) {
        if(propostas.size() < 1){
            throw new BussinesRuleException("É necessário que envie no minímo uma proposta");
        }

        Solicitacao solicitacao = solicitacaoRepository.findByUuid(solicitacaoUuid);
        if(solicitacao == null){
            throw new EntityNotFoundException("Nenhuma solicitação encontrada para o id "+solicitacaoUuid);
        }

        Medico medico = medicoRepository.findByUuid(medicoUuid);
        if(medico == null){
            throw new EntityNotFoundException("Nenhum médico encontrado para o id "+medicoUuid);
        }

        if(solicitacao.getDataParaAtendimento().isAfter(LocalDate.now())){
            throw new BussinesRuleException("A solicitação tinha data solicitada para uma data anterior a hoje");
        }

        List<Proposta> propostasList = new ArrayList<>();
        propostas.forEach(propostaForm -> {
            Proposta proposta = propostaMapper.toModel(propostaForm);
            if(solicitacao.getDataParaAtendimento().isAfter(proposta.getDataAtendimento())){
                throw new BussinesRuleException("A proposta enviada com a data de atendimento "+proposta.getDataAtendimento()+" é inválida para a solicitação");
            }

            if(proposta.getHoraInicial().isAfter(proposta.getHoraFinal())){
                throw new BussinesRuleException("A proposta tem o horário inicial posterior ao horário final previsto");
            }

            if(proposta.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BussinesRuleException("Não pode ter seu valor negativo e nem igual a ZERO");
            }

            proposta.setSituacao(SituacaoProposta.CRIADA);
            proposta.setMedico(medico);
            proposta.setSolicitacao(solicitacao);
            propostasList.add(proposta);
        });

        propostaRepository.saveAll(propostasList);

        return new ResponsePadraoParaAtualizacaoRecursoDTO("Propostas realizadas com sucesso");
    }

    public ResponsePadraoParaAtualizacaoRecursoDTO deletarProposta(UUID propostaUuid, UUID medicoUuid){
        Proposta proposta = propostaRepository.findByUuid(propostaUuid);

        if(proposta == null){
            throw new EntityNotFoundException("Nenhuma proposya encontrada para o id "+propostaUuid);
        }

        Medico medico = medicoRepository.findByUuid(medicoUuid);
        if(medico == null){
            throw new EntityNotFoundException("Nenhum médico encontrado para o id "+medicoUuid);
        }

        if(proposta.getSituacao().equals(SituacaoProposta.ACEITA)){
            throw new BussinesRuleException("A proposta já encontra-se aceita não podendo ser apagada");
        }

        propostaRepository.deleteById(proposta.getId());
        return new ResponsePadraoParaAtualizacaoRecursoDTO("Proposta apagada com sucesso");
    }
    
}
