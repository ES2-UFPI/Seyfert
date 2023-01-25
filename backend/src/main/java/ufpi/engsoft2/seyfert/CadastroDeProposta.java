/*
Um médico pode cadastrar quantas propostas quiser para diferentes solicitações seguindo as seguintes regras:
    - Uma proposta só pode ser considerada válida se o médico não tiver nenhuma consulta já marcada para o mesmo horário e data.
    - Uma proposta não pode ser cadastrada fora da data mostrada na solicitação.
        Exemplos: Solicitação Ortopedia 20/01/2023 de 10:00 às 12:00. O médico não pode cadastrar uma proposta para o dia 19/01/2023 e nem 21/01/2023. Embora possa cadastrar fora do horário especificado.
    - Em nenhuma hipótese um médico pode cadastrar propostas em especialidades que não sejam as suas.
*/

