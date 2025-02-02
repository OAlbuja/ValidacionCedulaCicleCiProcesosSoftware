import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { CedulaValidatorComponent } from './app/cedula-validator/cedula-validator.component';
import { FormsModule } from '@angular/forms';

bootstrapApplication(CedulaValidatorComponent, {
  providers: [importProvidersFrom(FormsModule), provideHttpClient()]
}).catch(err => console.error(err));
