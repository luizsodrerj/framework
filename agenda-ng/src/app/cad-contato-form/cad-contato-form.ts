import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { TextareaModule } from 'primeng/textarea';
import { ButtonModule } from 'primeng/button';
import { ActivatedRoute } from '@angular/router';
import { AgendaService } from '../service/agenda-service';
import { DialogModule } from 'primeng/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cad-contato-form',
  imports: [
    InputTextModule, FormsModule,
    TextareaModule, ButtonModule,
    DialogModule
  ],
  templateUrl: './cad-contato-form.html',
  styleUrl: './cad-contato-form.css',
  providers: [AgendaService]
})
export class CadContatoForm implements OnInit {

    service: AgendaService = inject(AgendaService)
    cdr: ChangeDetectorRef = inject(ChangeDetectorRef)
    router: Router = inject(Router)

    transactionType = ''
    dlgVisible = false

    contato = {
        id : '',
        contato : '',
        referencia : '',
        endereco : '',
        telefones : '',
        emails : '',
        obs : ''
    }


    save() {
        if (this.transactionType == 'create') {
            this.create()
        } else {
            this.update()
        }
    }

    create() {
        this.contato.id = ''
        this.service.createContato(this.contato).pipe().subscribe({
            next: (data) => {
                this.router.navigate(['/contatos-form']);
            }
        })
    }

    update() {
        this.service.update(this.contato).pipe().subscribe({
            next: (data) => {
                this.router.navigate(['/contatos-form']);
            }
        })
    }

    removeContato() {
        this.service.remove(this.contato.id).pipe().subscribe({
            next: (data) => {
                this.router.navigate(['/contatos-form']);
            }
        })
    }

    cancelar() {
        this.router.navigate(['/contatos-form']);
    }

    ngOnInit() {
        if (this.transactionType == 'update') {
            this.service.getById(this.contato.id).pipe().subscribe({
                next: (data) => {
                  this.contato.id =		      data.id
                  this.contato.contato =    data.contato
                  this.contato.referencia = data.referencia
                  this.contato.endereco =   data.endereco
                  this.contato.telefones =  data.telefones
                  this.contato.emails =     data.emails
                  this.contato.obs =        data.obs
                  this.cdr.detectChanges()
                }
            })
        }
    }

    constructor(private route: ActivatedRoute) {
        this.route.params.subscribe(params => {
            this.transactionType = params['transacType']
            this.contato.id = params['id']
        });
    }

}
