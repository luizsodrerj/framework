import { Routes } from '@angular/router';
import { ContatosForm } from './contatos-form/contatos-form';
import { CadContatoForm } from './cad-contato-form/cad-contato-form';

export const routes: Routes = [
  { path: 'contatos-form', component: ContatosForm },
  { path: 'cad-contato-form/:transacType/:id', component: CadContatoForm }
];
