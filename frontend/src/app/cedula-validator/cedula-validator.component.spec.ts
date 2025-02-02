import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CedulaValidatorComponent } from './cedula-validator.component';
import { CedulaService } from '../services/cedula.service';
import { of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('CedulaValidatorComponent', () => {
  let component: CedulaValidatorComponent;
  let fixture: ComponentFixture<CedulaValidatorComponent>;
  let cedulaServiceSpy: jasmine.SpyObj<CedulaService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('CedulaService', ['validarCedula']);

    await TestBed.configureTestingModule({
      imports: [FormsModule, HttpClientTestingModule],
      declarations: [CedulaValidatorComponent],
      providers: [{ provide: CedulaService, useValue: spy }]
    }).compileComponents();

    cedulaServiceSpy = TestBed.inject(CedulaService) as jasmine.SpyObj<CedulaService>;
  });

  beforeEach(() => {
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
    cedulaServiceSpy.validarCedula.and.returnValue(of(dummyResponse));

    component.cedula = '0931811087';
    component.validar();

    expect(component.resultado).toEqual(dummyResponse);
  });
});

// Mejoras añadidas:
// ✅ Simula una respuesta del servicio sin necesidad de hacer peticiones reales.
// ✅ Verifica que el mensaje de error se muestre si el usuario no ingresa una cédula.
// ✅ Comprueba que el resultado se actualiza correctamente al llamar al servicio.
