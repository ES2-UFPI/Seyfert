package ufpi.engsoft2.seyfert.shared.utils;

import java.util.Random;

public class GeradorCodigoValidacaoConsulta {
    private static final int VALOR_DO_PRIMEIRO_CARACTERE = 65;
    private static final int QUANTIDADE_DE_LETRAS = 26;
    private static final int VALOR_MAXIMO_DE_NUMEROS = 10;
    private String exemploDeCodigo = "4F-2D-6A-4N";

    private static char transformeNumeroEmCaracter(int numero){
        return (char)numero;
    }

    private static int gerarNumero(int numeroLimite){
        Random gerador = new Random();
        return gerador.nextInt(numeroLimite);
    }

    private static char gerarCaracter(){
        int numeroGerado = gerarNumero(QUANTIDADE_DE_LETRAS);
        return transformeNumeroEmCaracter(numeroGerado + VALOR_DO_PRIMEIRO_CARACTERE);
    }

    public static String gerarCodigo(){
        StringBuilder builder = new StringBuilder();
        boolean isLetra = false;
        int parDeCaracteres = 2;
        int contagemDePar = 0;
        for (int i = 0; i < 11; i++) {
            if(contagemDePar < parDeCaracteres){
                if(isLetra){
                    builder.append(gerarCaracter());
                    isLetra = false;
                }else {
                    builder.append(gerarNumero(VALOR_MAXIMO_DE_NUMEROS));
                    isLetra = true;
                }
                
                contagemDePar++;
            }else {
                builder.append('-');
                contagemDePar = 0;
            }
        }

        return builder.toString();
    }
}
