package br.edu.ifce.fomedegolservico.core.util;

import java.text.Normalizer;

public class PatternUtil {
    private PatternUtil() {}

    public static boolean isCPFValido(Long cpf){
        // Transforma o CPF em uma String de 11 dígitos com zeros à esquerda
        String cpfString = String.format("%011d", cpf);

        // Verifica se o CPF tem todos os dígitos iguais (ex.: 00000000000)
        if (cpfString.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (10 - i) * (cpfString.charAt(i) - '0');
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) {
            firstDigit = 0;
        }

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (11 - i) * (cpfString.charAt(i) - '0');
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) {
            secondDigit = 0;
        }

        // Verifica se os dígitos verificadores calculados são iguais aos dígitos verificadores do CPF
        return (cpfString.charAt(9) - '0') == firstDigit && (cpfString.charAt(10) - '0') == secondDigit;
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

}
