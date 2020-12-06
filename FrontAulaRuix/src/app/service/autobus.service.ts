import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Autobus } from '../models/autobus';

@Injectable({
  providedIn: 'root'
})
export class AutobusService {

  autobusUrl = 'http://localhost:8080/autobus/';

  constructor(private httpClient: HttpClient) { }

  public getComedores(): Observable<Autobus[]> {
    return this.httpClient.get<Autobus[]>(this.autobusUrl + 'autobus');
  }

  public save(autobus: Autobus): Observable<any> {
    return this.httpClient.post<any>(this.autobusUrl + 'create', autobus);
  }

  public update(id: number, autobus: Autobus): Observable<any> {
    return this.httpClient.put<any>(this.autobusUrl + `update/${id}`, autobus);
  }

  public inscribe(nombreUsuario: string): Observable<any> {
    return this.httpClient.get<any>(this.autobusUrl + `inscribe/${nombreUsuario}`);
  }

  public desInscribe(): Observable<any> {
    return this.httpClient.get<any>(this.autobusUrl + `desInscribe`);
  }

}
