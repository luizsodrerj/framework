import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AgendaService {

    private port = '8888'
    //private port = '8080'

    private API = `http://localhost:${this.port}/agenda-back-app/api/contatos`;

    constructor(private http: HttpClient) { }


    update(contato: any) {
        let endpoint = `${this.API}`;
        return this.http.put<any>(endpoint, contato);
    }

    createContato(contato: any) {
        let endpoint = `${this.API}/new-contato`;
        return this.http.post<any>(endpoint, contato);
    }

    remove(id: any): Observable<any> {
        let endpoint = `${this.API}/${id}`;
        return this.http.delete<any>(endpoint);
    }

    getById(id: any): Observable<any> {
        let endpoint = `${this.API}/${id}`;
        return this.http.get<any>(endpoint);
    }

    autoComplete(suggest: any): Observable<any> {
        let endpoint = `${this.API}/autocomplete/${suggest}`;
        return this.http.get<any>(endpoint);
    }

    getAll(): Observable<any> {
        let endpoint = `${this.API}/all`;
        return this.http.get<any>(endpoint);
    }

}
