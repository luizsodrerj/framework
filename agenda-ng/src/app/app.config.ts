import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { providePrimeNG } from 'primeng/config';
import { routes } from './app.routes';
import Nora from '@primeuix/themes/nora';
import { DatePipe } from '@angular/common';
import { provideHttpClient  } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    DatePipe,
    provideHttpClient(),
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes)
    ,providePrimeNG({
            theme: {
                preset: Nora
            },
        })
  ]

/*
 */
};
