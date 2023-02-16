package ufpi.engsoft2.seyfert.service.consulta.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ufpi.engsoft2.seyfert.domain.dto.ConsultaDTO;
import ufpi.engsoft2.seyfert.domain.dto.ResponsePadraoParaAtualizacaoRecursoDTO;
import ufpi.engsoft2.seyfert.domain.enums.SituacaoConsulta;
import ufpi.engsoft2.seyfert.domain.model.Consulta;
import ufpi.engsoft2.seyfert.domain.repository.ConsultaRepository;
import ufpi.engsoft2.seyfert.service.consulta.ConsultaMapper;
import ufpi.engsoft2.seyfert.service.consulta.ConsultaService;
import ufpi.engsoft2.seyfert.shared.exception.BussinesRuleException;
import ufpi.engsoft2.seyfert.shared.exception.EntityNotFoundException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaMapper consultaMapper;

    @Override
    public ConsultaDTO getConsulta(UUID uuid) {
        Consulta consulta = consultaRepository.findByUuid(uuid);
        if (consulta == null) {
            throw new EntityNotFoundException("Consulta não encontrada");
        }

        return consultaMapper.toDto(consulta);
    }

    @Override
    public ResponsePadraoParaAtualizacaoRecursoDTO validarConsulta(UUID consultaUuid, String codigoValidacao) {
       Consulta consulta = consultaRepository.findByUuid(consultaUuid);

       if(consulta == null){
            throw new EntityNotFoundException("Nenhuma consulta encontrada para o id "+consultaUuid);
       }

       if(codigoValidacao.length() != 11){
            throw new BussinesRuleException("O código enviado não tem a estrutura esperada");
       }

       if(!consulta.getCodigoVerificacao().equalsIgnoreCase(codigoValidacao)){
            throw new BussinesRuleException("Código enviado não válido para validação da consulta "+consultaUuid);
       }else {
           consulta.setSituacao(SituacaoConsulta.VALIDADA);
           consultaRepository.save(consulta);

           return new ResponsePadraoParaAtualizacaoRecursoDTO("Consulta validada");
       }

        
    }
    
}
