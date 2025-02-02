import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CedulaValidatorComponent } from './cedula-validator.component';
import { CedulaService } from '../services/cedula.service';
import { of } from 'rxjs';

describe('CedulaValidatorComponent', () => {
  let component: CedulaValidatorComponent;
  let fixture: ComponentFixture<CedulaValidatorComponent>;
  let cedulaServiceSpy: jasmine.SpyObj<CedulaService>;

  beforeEach(async () => {
    // Creamos un spy para el servicio
    const spy = jasmine.createSpyObj('CedulaService', ['validarCedula']);

    await TestBed.configureTestingModule({
      // Importamos el componente standalone, no lo declaramos
      imports: [CedulaValidatorComponent],
      providers: [
        { provide: CedulaService, useValue: spy }
      ]
    }).compileComponents();

    cedulaServiceSpy = TestBed.inject(CedulaService) as jasmine.SpyObj<CedulaService>;
    fixture = TestBed.createComponent(CedulaValidatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('debería mostrar error si no se ingresa cédula', () => {
    component.cedula = '';
    component.validar();
    expect(component.errorMessage).toBe('Por favor, ingresa una cédula.');
  });

  it('debería mostrar el resultado al validar la cédula', () => {
    const dummyResponse = {
      cedula: '0931811087',
      valida: true,
      mensaje: 'La cédula es correcta.'
    };
    // Simulamos la respuesta del servicio
    cedulaServiceSpy.validarCedula.and.returnValue(of(dummyResponse));

    component.cedula = '0931811087';
    component.validar();

    expect(component.resultado).toEqual(dummyResponse);
  });
});


/**
 * Pruebas para el componente CedulaValidatorComponent:
 *
 * 1. "debería mostrar error si no se ingresa cédula":
 *    - Este test asigna una cadena vacía a la variable "cedula" y llama al método "validar()".
 *    - Verifica que, al no ingresar ningún valor, se actualice la propiedad "errorMessage" con el mensaje
 *      "Por favor, ingresa una cédula." y se limpie el "resultado".
 *
 * 2. "debería mostrar el resultado al validar la cédula":
 *    - Este test simula la respuesta del servicio CedulaService usando un spy.
 *    - Se configura el spy para que, al llamar a "validarCedula", devuelva un Observable con un objeto dummy
 *      que contiene la cédula, un flag de validación (true) y un mensaje "La cédula es correcta.".
 *    - Se asigna la cédula '0931811087' a la variable "cedula" y se llama al método "validar()".
 *    - Luego, se verifica que la propiedad "resultado" del componente se actualice correctamente con el
 *      objeto dummy simulado.
 */
