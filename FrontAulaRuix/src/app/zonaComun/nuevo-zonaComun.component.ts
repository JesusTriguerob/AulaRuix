import { Component, OnInit } from '@angular/core';
import { ZonaComunService } from '../service/zonaComun.service';
import { HoraService } from '../service/hora.service';
import { ZonaComun } from '../models/zonaComun';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { TokenService } from '../service/token.service';
import { Hora } from '../models/hora';

@Component({
  selector: 'app-nuevo-zonas',
  templateUrl: './nuevo-zonaComun.component.html',
  styleUrls: ['./nuevo-zonaComun.component.css']
})
export class NuevoZonaComunComponent implements OnInit {

  nombre = '';
  horas: Hora[] = [];
  roles: string[];
  isAdmin = false;

  constructor(
    private zonaComunService: ZonaComunService,
    private horaService: HoraService,
    private toastr: ToastrService,
    private router: Router,
    private tokenService: TokenService
    ) { }

  ngOnInit() {
  }

  onCreate(): void {
    this.roles = this.tokenService.getAuthorities();
    this.horaService.lista().subscribe(
      data => {
        this.horas = data;
      },
      err => {
        console.log(err);
      }
    );
    this.roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
    const zonaComun = new ZonaComun(this.nombre, this.horas);
    this.zonaComunService.save(zonaComun).subscribe(
      data => {
        this.toastr.success('Zona Creada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/zona']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        // this.router.navigate(['/']);
      }
    );
  }

}
