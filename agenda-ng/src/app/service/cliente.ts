import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClienteService {

    private API = 'http://localhost:8080/cadclientes/api/cliente';

    constructor (private http: HttpClient) {
    }

    getAssociatedWithParentCompany(): Observable<any> {
        let endpoint = `${this.API}/filiais-associadas`;
        return this.http.get<any>(endpoint);
    }

    getById(id: any): Observable<any> {
        let endpoint = `${this.API}/${id}`;
        return this.http.get<any>(endpoint);
    }

    associateWithParentCompany(filial: any) {
        let endpoint = `${this.API}/associar-matriz`
        return this.http.put<any>(endpoint, filial);
    }

    update(cliente: any): Observable<any> {
        return this.http.put<any>(this.API, cliente);
    }

    getFiliais(): Observable<any> {
        let endpoint = `${this.API}/filiais`;
        return this.http.get<any>(endpoint);
    }

    getMatrizes(paramId: string): Observable<any> {
        let queryParams = `?id=${paramId}`;
        let endpoint    = `${this.API}/matrizes${queryParams}`;
        return this.http.get<any>(endpoint);
    }

    find(params: any): Observable<any> {
        let queryParams = `?nome=${params.nomeRazao}&date=${params.dataCadastro}`
        let endpoint = `${this.API}/get-by-cliente${queryParams}`;
        return this.http.get<any>(endpoint);
    }

    getAll(): Observable<any> {
        let endpoint = `${this.API}/all`;
        return this.http.get<any>(endpoint);
    }

    persist(cliente: any): Observable<any> {
        let endpoint = `${this.API}/new-cliente`;
        return this.http.post<any>(`${endpoint}`, cliente);
    }

}
