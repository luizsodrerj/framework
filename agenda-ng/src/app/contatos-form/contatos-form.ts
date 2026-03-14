import { Component } from '@angular/core';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';

interface AutoCompleteCompleteEvent {
    originalEvent: Event;
    query: string;
}

@Component({
  selector: 'contatos-form',
  imports: [AutoCompleteModule, FormsModule, ButtonModule],
  templateUrl: './contatos-form.html',
  styleUrl: './contatos-form.css',
})
export class ContatosForm {
    items: any[] = [];
    value: any;

    search(event: AutoCompleteCompleteEvent) {

    }

}
