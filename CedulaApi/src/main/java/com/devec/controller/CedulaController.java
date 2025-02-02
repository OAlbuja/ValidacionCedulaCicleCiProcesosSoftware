package com.devec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devec.service.CedulaValidatorService;

@RestController
@RequestMapping("/api/cedulas")
@CrossOrigin(origins = "http://localhost:4200")
public class CedulaController {

    private final CedulaValidatorService cedulaValidatorService;

    public CedulaController(final CedulaValidatorService cedulaValidatorService) {
        this.cedulaValidatorService = cedulaValidatorService;
    }

    // Endpoint GET para validar la cédula: /api/cedulas/validar?cedula=0931811087
    @GetMapping("/validar")
    public ResponseEntity<Map<String, Object>> validarCedula(@RequestParam("cedula") final String cedula) {
        boolean esValida = cedulaValidatorService.validarCedula(cedula);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("cedula", cedula);
        respuesta.put("valida", esValida);
        respuesta.put("mensaje", esValida ? "La cédula es correcta." : "La cédula es incorrecta o inválida.");

        return ResponseEntity.ok(respuesta);
    }
}
