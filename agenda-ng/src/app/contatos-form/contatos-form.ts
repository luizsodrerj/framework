import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { AgendaService } from '../service/agenda-service';

interface AutoCompleteCompleteEvent {
    originalEvent: Event;
    query: string;
}

@Component({
  selector: 'contatos-form',
  imports: [AutoCompleteModule, FormsModule, ButtonModule],
  templateUrl: './contatos-form.html',
  styleUrl: './contatos-form.css',
  providers: [AgendaService]
})
export class ContatosForm {

    private service: AgendaService = inject(AgendaService)
    private cdr: ChangeDetectorRef = inject(ChangeDetectorRef)
    private router: Router = inject(Router)

    items: any[] = [];
    value: any;


    onClickBtSelecionar() {
        this.router.navigate([
            '/cad-contato-form',
            'update',
            this.value.id
        ]);
    }

    onClickBtNovo() {
        this.router.navigate([
            '/cad-contato-form',
            'create',
            '0'
        ]);
    }

    search(event: AutoCompleteCompleteEvent) {
        let query = event.query

        if (event.query) {
            this.service.autoComplete(query).pipe().subscribe({
                next: (data) => {
                  this.items = data
                  this.cdr.detectChanges()
                }
            })
        } else {
            this.service.getAll().pipe().subscribe({
                next: (data) => {
                  this.items = data;
                  this.cdr.detectChanges()
                }
            })
        }

    }

}
