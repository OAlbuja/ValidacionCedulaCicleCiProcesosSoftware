import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface CedulaResponse {
  cedula: string;
  valida: boolean;
  mensaje: string;
}

@Injectable({
  providedIn: 'root'
})
export class CedulaService {
  private apiUrl = 'http://localhost:8080/api/cedulas/validar';

  constructor(private http: HttpClient) {}

  validarCedula(cedula: string): Observable<CedulaResponse> {
    return this.http.get<CedulaResponse>(`${this.apiUrl}?cedula=${cedula}`);
  }
}
