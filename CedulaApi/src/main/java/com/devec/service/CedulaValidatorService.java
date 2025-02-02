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
            int region = Integer.parseInt(cedula.substring(0, 2));
            if (region < REGION_MIN || region > REGION_MAX) {
                return false;
            }

            int ultimoDigito = Character.getNumericValue(cedula.charAt(9));
            int sumaPares = Character.getNumericValue(cedula.charAt(1)) +
                            Character.getNumericValue(cedula.charAt(3)) +
                            Character.getNumericValue(cedula.charAt(5)) +
                            Character.getNumericValue(cedula.charAt(7));

            int sumaImpares = 0;
            for (int i = 0; i < 9; i += 2) {
                int valor = Character.getNumericValue(cedula.charAt(i)) * MULTIPLICADOR_IMPAR;
                if (valor > 9) {
                    valor -= 9;
                }
                sumaImpares += valor;
            }

            int sumaTotal = sumaPares + sumaImpares;
            int digitoValidador = (sumaTotal / DIVISOR + 1) * DIVISOR - sumaTotal;
            if (digitoValidador == DIVISOR) {
                digitoValidador = 0;
            }

            return digitoValidador == ultimoDigito;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
