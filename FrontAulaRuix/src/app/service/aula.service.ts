import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Aula } from '../models/aula';

@Injectable({
  providedIn: 'root'
})
export class AulaService {

  aulaURL = 'http://localhost:8080/aula/';

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Aula[]> {
    return this.httpClient.get<Aula[]>(this.aulaURL + 'aulas');
  }

  public update(id: number, aula: Aula): Observable<any> {
    return this.httpClient.put<any>(this.aulaURL + `update/${id}`, aula);
  }

  public reservar(id: number, hora: number): Observable<any> {
    return this.httpClient.post<any>(this.aulaURL + `reservar/${id}`, hora);
  }

  public save(aula: Aula): Observable<any> {
    return this.httpClient.post<any>(this.aulaURL + 'create', aula);
  }

}
