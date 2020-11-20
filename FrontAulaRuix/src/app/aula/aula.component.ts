import { Component, OnInit } from '@angular/core';
import { Aula } from '../models/aula';
import { AulaService } from '../service/aula.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';
import { HoraService } from '../service/hora.service';
import { Hora } from '../models/hora';

@Component({
  selector: 'app-aula',
  templateUrl: './aula.component.html',
  styleUrls: ['./aula.component.css']
})
export class AulaComponent implements OnInit {

  aulas: Aula[] = [];
  horas: Hora[] = [];
  roles: string[];
  isAdmin = false;
  nombreUsuario: string;

  constructor(
    private aulaService: AulaService,
    private horaService: HoraService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.cargarAulas();
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

  cargarAulas(): void {
    this.aulaService.lista().subscribe(
      data => {
        this.aulas = data;
        console.log(data);

      },
      err => {
        console.log(err);
      }
    );
  }

  reservar(id: number, hora: number) {
    this.aulaService.reservar(id,hora).subscribe(
      data => {
        this.cargarAulas();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
}
