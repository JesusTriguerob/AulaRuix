import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comedor } from '../models/comedor';

@Injectable({
  providedIn: 'root'
})
export class ComedorService {

  comedorURL = 'http://localhost:8080/comedor/';

  constructor(private httpClient: HttpClient) { }

  public getComedores(): Observable<Comedor[]> {
    return this.httpClient.get<Comedor[]>(this.comedorURL + 'comedor');
  }

  public save(comedor: Comedor): Observable<any> {
    return this.httpClient.post<any>(this.comedorURL + 'create', comedor);
  }

  public update(id: number, comedor: Comedor): Observable<any> {
    return this.httpClient.put<any>(this.comedorURL + `update/${id}`, comedor);
  }

}
