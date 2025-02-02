package com.devec.service;

import org.springframework.stereotype.Service;

@Service
public class CedulaValidatorService {

    public boolean validarCedula(String cedula) {
        // Validamos que la cédula tenga 10 dígitos
        if (cedula == null || cedula.length() != 10) {
            return false;
        }

        try {
            // Obtenemos los dos primeros dígitos para validar la región (1-24)
            int region = Integer.parseInt(cedula.substring(0, 2));
            if (region < 1 || region > 24) {
                return false;
            }

            // Extraemos el último dígito (dígito verificador)
            int ultimoDigito = Character.getNumericValue(cedula.charAt(9));

            // Suma de los dígitos en posiciones pares: índices 1, 3, 5 y 7 (recordando que el índice empieza en 0)
            int sumaPares = Character.getNumericValue(cedula.charAt(1)) +
                            Character.getNumericValue(cedula.charAt(3)) +
                            Character.getNumericValue(cedula.charAt(5)) +
                            Character.getNumericValue(cedula.charAt(7));

            // Procesamos los dígitos en posiciones impares: índices 0, 2, 4, 6 y 8
            int sumaImpares = 0;
            int[] indicesImpares = {0, 2, 4, 6, 8};
            for (int indice : indicesImpares) {
                int valor = Character.getNumericValue(cedula.charAt(indice));
                int prod = valor * 2;
                // Si el producto es mayor que 9, se le resta 9
                if (prod > 9) {
                    prod -= 9;
                }
                sumaImpares += prod;
            }

            // Suma total de los dígitos procesados
            int sumaTotal = sumaPares + sumaImpares;

            // Extraemos el primer dígito de la suma total y calculamos la decena inmediata
            int primerDigitoSuma = Integer.parseInt(Integer.toString(sumaTotal).substring(0, 1));
            int decenaInmediata = (primerDigitoSuma + 1) * 10;

            // Obtenemos el dígito validador restando la suma total a la decena inmediata
            int digitoValidador = decenaInmediata - sumaTotal;
            if (digitoValidador == 10) {
                digitoValidador = 0;
            }

            // La cédula es válida si el dígito validador coincide con el último dígito de la cédula
            return digitoValidador == ultimoDigito;
        } catch (NumberFormatException e) {
            // Si hay problemas al parsear algún dígito, la cédula es inválida
            return false;
        }
    }
}

