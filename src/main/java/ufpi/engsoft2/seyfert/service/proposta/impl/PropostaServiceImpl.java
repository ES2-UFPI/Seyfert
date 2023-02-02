package ufpi.engsoft2.seyfert.service.proposta.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ufpi.engsoft2.seyfert.domain.dto.PropostaDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoProposta;
import ufpi.engsoft2.seyfert.domain.form.PropostaForm;
import ufpi.engsoft2.seyfert.domain.model.EspecialidadeMedica;
import ufpi.engsoft2.seyfert.domain.model.Medico;
import ufpi.engsoft2.seyfert.domain.model.Proposta;
import ufpi.engsoft2.seyfert.domain.model.Solicitacao;
import ufpi.engsoft2.seyfert.domain.repository.MedicoRepository;
import ufpi.engsoft2.seyfert.domain.repository.PropostaRepository;
import ufpi.engsoft2.seyfert.domain.repository.SolicitacaoRepository;
import ufpi.engsoft2.seyfert.service.proposta.PropostaService;
import ufpi.engsoft2.seyfert.service.proposta.PropostaServiceMapper;

@Service
public class PropostaServiceImpl implements PropostaService{
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

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

    public PropostaDTO cadastrarProposta(PropostaForm form){ 
        UUID medicoId = form.getMedicoId();
        UUID solicitacaoId = form.getSolicitacaoUuid();

        Medico medico = medicoRepository.findByUuid(medicoId);
        Solicitacao solicitacao = solicitacaoRepository.findByUuid(solicitacaoId);

        EspecialidadeMedica especialidadeSolicitacao = solicitacao.getEspecialidadeMedica();
        List<EspecialidadeMedica> especialidades = medico.getEspecialidades();
        
        //Checar validade da especialidade do medico
        //if(validarEspecialidade(especialidadeSolicitacao, especialidades) throw new ExcecaoDeEspecialidade("Especialidade do médico não está de acordo com a solicitação.\n");

        //Checar se a proposta foi feita para o dia da solicitacao
        //if(dataProposta != dataSolicitacao) throw new ExcecaoDeDataDeSolicitacao("Data da proposta não está de acordo com a data solicitada.\n");

        //Checar disponibilidade do medico para o dia/horario
        //if(!dataDisponivel(data)) throw new ExcecaoDeDisponibilidadeData("Data da proposta não está de acordo com a data solicitada.\n");
        //if(!horarioDisponivel(horario)) throw new ExcecaoDeDisponibilidadeHorario("Data da proposta não está de acordo com a data solicitada.\n");

        //Cadastrar proposta
        Proposta proposta = propostaServiceMapper.toModel(form);
        propostaRepository.save(proposta);

        return propostaServiceMapper.toDto(proposta);
    }
    
    // private Boolean validarEspecialidade(){
    //     for (EspecialidadeMedica especialidadeMedico : especialidades) {
    //         if(especialidadeMedico.getNomeEspecialidade() == especialidadeSolicitacao.getNomeEspecialidade());
    //     }
    // }
}
