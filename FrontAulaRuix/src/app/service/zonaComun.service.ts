import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ZonaComun } from '../models/zonaComun';

@Injectable({
  providedIn: 'root'
})
export class ZonaComunService {

  zonaComunURL = 'http://localhost:8080/zona/';

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<ZonaComun[]> {
    return this.httpClient.get<ZonaComun[]>(this.zonaComunURL + 'zonas');
  }

  public update(id: number, zonaComun: ZonaComun): Observable<any> {
    return this.httpClient.put<any>(this.zonaComunURL + `update/${id}`, zonaComun);
  }

  public reservar(id: number, hora: number): Observable<any> {
    return this.httpClient.post<any>(this.zonaComunURL + `reservar/${id}`, hora);
  }

  public save(zonaComun: ZonaComun): Observable<any> {
    return this.httpClient.post<any>(this.zonaComunURL + 'create', zonaComun);
  }

}
