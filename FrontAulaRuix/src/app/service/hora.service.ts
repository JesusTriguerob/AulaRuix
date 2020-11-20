import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hora } from '../models/hora';

@Injectable({
  providedIn: 'root'
})
export class HoraService {

  horaURL = 'http://localhost:8080/hora/';

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Hora[]> {
    return this.httpClient.get<Hora[]>(this.horaURL + 'horas');
  }


}
