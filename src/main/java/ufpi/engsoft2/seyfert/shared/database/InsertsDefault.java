package ufpi.engsoft2.seyfert.shared.database;

import java.time.LocalDate;
import java.util.Arrays;

import javax.print.attribute.standard.Media;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ufpi.engsoft2.seyfert.domain.enums.TipoEndereco;
import ufpi.engsoft2.seyfert.domain.model.Endereco;
import ufpi.engsoft2.seyfert.domain.model.EspecialidadeMedica;
import ufpi.engsoft2.seyfert.domain.model.Medico;
import ufpi.engsoft2.seyfert.domain.model.Paciente;
import ufpi.engsoft2.seyfert.domain.model.Solicitacao;
import ufpi.engsoft2.seyfert.domain.repository.EndereRepository;
import ufpi.engsoft2.seyfert.domain.repository.EspecialidadeMedicaRepository;
import ufpi.engsoft2.seyfert.domain.repository.MedicoRepository;
import ufpi.engsoft2.seyfert.domain.repository.PacienteRepository;
import ufpi.engsoft2.seyfert.domain.repository.SolicitacaoRepository;

@RequiredArgsConstructor
@Service
public class InsertsDefault {
    
    private final EspecialidadeMedicaRepository especialidadeMedicaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final EndereRepository endereRepository;
    private final SolicitacaoRepository solicitacaoRepository;

    public void popularBanco(){
        EspecialidadeMedica especialidadeMedica = EspecialidadeMedica
        .builder()
        .descricao("Uma especialidade")
        .nomeEspecialidade("Reumatismo")
        .tempoMedioConsultaEmMinutos(30)
        .build();

        especialidadeMedica = especialidadeMedicaRepository.save(especialidadeMedica);

        
        Medico medico = new Medico();
        medico.setCrm("45812-PI");
        medico.setEspecialidades(Arrays.asList(especialidadeMedica));
        medico.setDescricao("É um bom médico");
        medico = medicoRepository.save(medico);
        
        Endereco endereco = Endereco
        .builder()
        .bairro("Centro")
        .cidade("Teresina")
        .complemento("Próximo ao predio")
        .estado("Piauí")
        .cep("645962592")
        .tipoEndereco(TipoEndereco.COMERCIAL)
        .build();
        endereco.setMedico(medico);
        endereRepository.save(endereco);


        Endereco endereco2 = Endereco
        .builder()
        .bairro("Centro")
        .cidade("Teresina")
        .complemento("Próximo ao predio")
        .estado("Piauí")
        .cep("645962592")
        .tipoEndereco(TipoEndereco.RESIDENCIAL)
        .build();

        Paciente paciente = new Paciente();
        paciente.setEndereco(endereco2);
        paciente.setNome("Járdesson Ribeiro");
        paciente = pacienteRepository.save(paciente);
        endereRepository.save(endereco2);


        Solicitacao solicitacao = Solicitacao
        .builder()
        .dataParaAtendimento(LocalDate.now().plusDays(10))
        .descricaoSolicitacao("Dor nas costas")
        .especialidadeMedica(especialidadeMedica)
        .paciente(paciente)
        .build();

        Solicitacao solicitacao2 = Solicitacao
        .builder()
        .dataParaAtendimento(LocalDate.now().plusDays(10))
        .descricaoSolicitacao("Dor nas costas")
        .especialidadeMedica(especialidadeMedica)
        .paciente(paciente)
        .build();

        Solicitacao solicitacao3 = Solicitacao
        .builder()
        .dataParaAtendimento(LocalDate.now().plusDays(10))
        .descricaoSolicitacao("Dor nas costas")
        .especialidadeMedica(especialidadeMedica)
        .paciente(paciente)
        .build();
        
        solicitacaoRepository.saveAll(Arrays.asList(solicitacao, solicitacao2, solicitacao3));
        
    }
}
