import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Dieta } from '../models/dieta';

@Injectable({
  providedIn: 'root'
})
export class DietaService {

  dietaURL = 'http://localhost:8080/dieta/';

  constructor(private httpClient: HttpClient) { }

  public getdietas(): Observable<Dieta[]> {
    return this.httpClient.get<Dieta[]>(this.dietaURL + 'dietas');
  }

  public save(dieta: Dieta): Observable<any> {
    return this.httpClient.post<any>(this.dietaURL + 'create', dieta);
  }

  public update(id: number, dieta: Dieta): Observable<any> {
    return this.httpClient.put<any>(this.dietaURL + `update/${id}`, dieta);
  }

  public inscribe(id: number): Observable<any> {
    return this.httpClient.get<any>(this.dietaURL + `inscribe/${id}`);
  }

  public desInscribe(): Observable<any> {
    return this.httpClient.get<any>(this.dietaURL + `desInscribe`);
  }
}
