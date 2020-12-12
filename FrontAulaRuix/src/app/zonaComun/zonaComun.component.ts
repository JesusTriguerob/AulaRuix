import { Component, OnInit } from '@angular/core';
import { ZonaComun } from '../models/zonaComun';
import { ZonaComunService } from '../service/zonaComun.service';
import { TokenService } from '../service/token.service';
import { HoraService } from '../service/hora.service';
import { Hora } from '../models/hora';
import swal from'sweetalert2';

@Component({
  selector: 'app-zonaComun',
  templateUrl: './zonaComun.component.html',
  styleUrls: ['./zonaComun.component.css']
})
export class ZonaComunComponent implements OnInit {

  zonas: ZonaComun[] = [];
  horas: Hora[] = [];
  roles: string[];
  isAdmin = false;
  nombreUsuario: string;

  constructor(
    private zonaComunService: ZonaComunService,
    private horaService: HoraService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.cargarZonas();
    this.horaService.lista().subscribe(
      data => {
        this.horas = data;
        console.log(this.horas);
      },
      err => {
        console.log(err);
      }
    );
    this.nombreUsuario = this.tokenService.getUserName();
    console.log(this.nombreUsuario);
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }

  cargarZonas(): void {
    this.zonaComunService.lista().subscribe(
      data => {
        this.zonas = data;
        console.log(data);

      },
      err => {
        console.log(err);
      }
    );
  }

  reservar(id: number, hora: number) {
    swal.fire({
    title: '¿Estas seguro que quieres alquilar esta zona?',
    text: "¡Una vez alquilado no hay vuelta atras!",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Sí, reservar'
  }).then((result) => {
    if (result.value) {

    this.zonaComunService.reservar(id,hora).subscribe(
        response => {
          this.cargarZonas();
          swal.fire(
            'Alquilado!',
            'La zona ha sido alquilada con éxito',
            'success'
          )
        }
      )
    }
  })
  }
}
