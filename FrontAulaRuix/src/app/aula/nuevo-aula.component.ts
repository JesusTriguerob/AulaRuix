import { Component, OnInit } from '@angular/core';
import { AulaService } from '../service/aula.service';
import { HoraService } from '../service/hora.service';
import { Aula } from '../models/aula';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { TokenService } from '../service/token.service';
import { Hora } from '../models/hora';

@Component({
  selector: 'app-nuevo-aula',
  templateUrl: './nuevo-aula.component.html',
  styleUrls: ['./nuevo-aula.component.css']
})
export class NuevoAulaComponent implements OnInit {

  nombre = '';
  horas: Hora[] = [];
  roles: string[];
  isAdmin = false;

  constructor(
    private aulaService: AulaService,
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
    const aula = new Aula(this.nombre, this.horas);
    this.aulaService.save(aula).subscribe(
      data => {
        this.toastr.success('Zona Creada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/aula']);
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
