/*
Um médico pode cadastrar quantas propostas quiser para diferentes solicitações seguindo as seguintes regras:
    - Uma proposta só pode ser considerada válida se o médico não tiver nenhuma consulta já marcada para o mesmo horário e data.
    - Uma proposta não pode ser cadastrada fora da data mostrada na solicitação.
        Exemplos: Solicitação Ortopedia 20/01/2023 de 10:00 às 12:00. O médico não pode cadastrar uma proposta para o dia 19/01/2023 e nem 21/01/2023. Embora possa cadastrar fora do horário especificado.
    - Em nenhuma hipótese um médico pode cadastrar propostas em especialidades que não sejam as suas.
*/

package ufpi.engsoft2.seyfert;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CadastroDeProposta{
    //### TESTE ###
    String especialidadeSolicitacao; String especialidadeMedico;
    LocalDate dataSolicitacao; LocalDate dataProposta;

    ArrayList<ConsultaDTO> consultas = getConsultas();

    public void cadastrarProposta(LocalTime horario, LocalDate data) throws ExcecaoDeEspecialidade, ExcecaoDeDataDeSolicitacao, ExcecaoDeDisponibilidadeData, ExcecaoDeDisponibilidadeHorario{
        //Checar validade da especialidade do medico
        if(especialidadeMedico != especialidadeSolicitacao) throw new ExcecaoDeEspecialidade("Especialidade do médico não está de acordo com a solicitação.\n");

        //Checar se a proposta foi feita para o dia da solicitacao
        if(dataProposta != dataSolicitacao) throw new ExcecaoDeDataDeSolicitacao("Data da proposta não está de acordo com a data solicitada.\n");

        //Checar disponibilidade do medico para o dia/horario
        if(!dataDisponivel(data)) throw new ExcecaoDeDisponibilidadeData("Data da proposta não está de acordo com a data solicitada.\n");
        if(!horarioDisponivel(horario)) throw new ExcecaoDeDisponibilidadeHorario("Data da proposta não está de acordo com a data solicitada.\n");
    
        //Cadastrar proposta
    }

    private ArrayList<ConsultaDTO> getConsultas(){
        ArrayList<ConsultaDTO> consultas = new ArrayList<ConsultaDTO>();

        //Retornar as consultas do medico

        return consultas;
    }

    private boolean dataDisponivel(LocalDate data){
        //Checar disponibilidade do medico para o dia
        for (ConsultaDTO consulta : consultas) {
            if(consulta.dataAtendimento == data) return false;
        }

        return true;
    }

    private boolean horarioDisponivel(LocalTime horario){
        //Checar disponibilidade do medico para o horario
        for (ConsultaDTO consulta : consultas) {
            if(consulta.horario == horario) return false;
        }

        return true;
    }

    public class ExcecaoDeEspecialidade extends Exception {
        public ExcecaoDeEspecialidade(String mensagem) {
            super(mensagem);
        }
    }
    public class ExcecaoDeDataDeSolicitacao extends Exception {
        public ExcecaoDeDataDeSolicitacao(String mensagem) {
            super(mensagem);
        }
    }
    public class ExcecaoDeDisponibilidadeData extends Exception {
        public ExcecaoDeDisponibilidadeData(String mensagem) {
            super(mensagem);
        }
    }
    public class ExcecaoDeDisponibilidadeHorario extends Exception {
        public ExcecaoDeDisponibilidadeHorario(String mensagem) {
            super(mensagem);
        }
    }
}