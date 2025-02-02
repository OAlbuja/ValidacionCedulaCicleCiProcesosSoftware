import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CedulaService, CedulaResponse } from '../services/cedula.service';

@Component({
  selector: 'app-cedula-validator',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './cedula-validator.component.html',
  styleUrls: ['./cedula-validator.component.css']
})
export class CedulaValidatorComponent {
  cedula: string = '';
  resultado: CedulaResponse | null = null;
  errorMessage: string = '';

  constructor(private cedulaService: CedulaService) {}

  validar(): void {
    if (!this.cedula.trim()) {
      this.errorMessage = 'Por favor, ingresa una cédula.';
      this.resultado = null;
      return;
    }
    this.errorMessage = '';
    this.cedulaService.validarCedula(this.cedula).subscribe({
      next: (res) => this.resultado = res,
      error: (err) => {
        this.errorMessage = 'Error al validar la cédula. Intenta nuevamente.';
        console.error(err);
      }
    });
  }
}
