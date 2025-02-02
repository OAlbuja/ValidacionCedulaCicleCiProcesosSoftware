package com.devec.service;

import org.springframework.stereotype.Service;

/**
 * Servicio para la validación de cédulas.
 */
@Service
public class CedulaValidatorService {

    private static final int CEDULA_LENGTH = 10;
    private static final int REGION_MIN = 1;
    private static final int REGION_MAX = 24;
    private static final int DIVISOR = 10;
    private static final int MULTIPLICADOR_IMPAR = 2;
    private static final int DIGITO_VALIDADOR_MAX = 9;
    private static final int INDICE_ULTIMO_DIGITO = 9;
    private static final int[] INDICES_PARES = {1, 3, 5, 7};
    private static final int MULTIPLICADOR_DIVISOR = 1;
    private static final int PRIMEROS_DOS_DIGITOS = 2;

    /**
     * Valida si una cédula es correcta según su estructura.
     *
     * @param cedula número de cédula a validar
     * @return true si la cédula es válida, false en caso contrario
     */
    public boolean validarCedula(final String cedula) {
        if (cedula == null || cedula.length() != CEDULA_LENGTH) {
            return false;
        }

        try {
            int region = Integer.parseInt(cedula.substring(0, PRIMEROS_DOS_DIGITOS));
            if (region < REGION_MIN || region > REGION_MAX) {
                return false;
            }

            int ultimoDigito = Character.getNumericValue(cedula.charAt(INDICE_ULTIMO_DIGITO));
            int sumaPares = 0;

            for (int indice : INDICES_PARES) {
                sumaPares += Character.getNumericValue(cedula.charAt(indice));
            }

            int sumaImpares = 0;
            for (int i = 0; i < INDICE_ULTIMO_DIGITO; i += 2) {
                int valor = Character.getNumericValue(cedula.charAt(i)) * MULTIPLICADOR_IMPAR;
                if (valor > DIGITO_VALIDADOR_MAX) {
                    valor -= DIGITO_VALIDADOR_MAX;
                }
                sumaImpares += valor;
            }

            int sumaTotal = sumaPares + sumaImpares;
            int digitoValidador = (sumaTotal / DIVISOR + MULTIPLICADOR_DIVISOR) * DIVISOR - sumaTotal;
            if (digitoValidador == DIVISOR) {
                digitoValidador = 0;
            }

            return digitoValidador == ultimoDigito;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
