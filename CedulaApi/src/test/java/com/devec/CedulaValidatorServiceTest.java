package com.devec;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.devec.service.CedulaValidatorService;

/**
 * Pruebas para CedulaValidatorService:
 * 
 * 1. testValidCedula:
 *    - Verifica que, dado un número de cédula válido (ej. "0931811087"), el servicio retorne true.
 * 
 * 2. testInvalidCedulaWrongLength:
 *    - Verifica que una cédula con longitud incorrecta (menos de 10 dígitos) se considere inválida.
 * 
 * 3. testInvalidCedulaInvalidRegion:
 *    - Verifica que una cédula con el código de región fuera del rango (1-24) retorne false.
 */
public class CedulaValidatorServiceTest {

    private final CedulaValidatorService service = new CedulaValidatorService();

    @Test
    void testValidCedula() {
        // La cédula "0931811087" es un ejemplo de cédula válida.
        boolean valid = service.validarCedula("0931811087");
        assertTrue(valid, "La cédula debe ser válida");
    }

    @Test
    void testInvalidCedulaWrongLength() {
        // Cédula con 9 dígitos (inválida)
        boolean valid = service.validarCedula("123456789");
        assertFalse(valid, "Una cédula con longitud incorrecta no debe ser válida");
    }

    @Test
    void testInvalidCedulaInvalidRegion() {
        // Cédula con los dos primeros dígitos fuera del rango 1-24 (ejemplo: "2534567890")
        boolean valid = service.validarCedula("2534567890");
        assertFalse(valid, "La cédula con código de región inválido no debe ser válida");
    }
}
