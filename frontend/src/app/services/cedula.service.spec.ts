import { TestBed } from '@angular/core/testing';
import { CedulaService, CedulaResponse } from './cedula.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('CedulaService', () => {
  let service: CedulaService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CedulaService]
    });
    service = TestBed.inject(CedulaService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verifica que no haya peticiones pendientes
  });

  it('debería validar la cédula correctamente', () => {
    const dummyResponse: CedulaResponse = {
      cedula: '0931811087',
      valida: true,
      mensaje: 'La cédula es correcta.'
    };

    service.validarCedula('0931811087').subscribe(response => {
      expect(response.valida).toBeTrue();
      expect(response.cedula).toBe('0931811087');
      expect(response.mensaje).toBe('La cédula es correcta.');
    });

    const req = httpMock.expectOne('http://localhost:8080/api/cedulas/validar?cedula=0931811087');
    expect(req.request.method).toBe('GET');
    req.flush(dummyResponse);
  });
});

// Mejoras añadidas:
// ✅ Simula una respuesta real del backend.
// ✅ Verifica que la URL generada sea correcta.
// ✅ Comprueba que el servicio haga la solicitud con GET.
// ✅ Se asegura de que no haya peticiones pendientes con httpMock.verify().
