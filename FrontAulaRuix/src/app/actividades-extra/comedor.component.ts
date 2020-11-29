import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dieta } from '../models/dieta';
import { Usuario } from '../models/usuario';
import { DietaService } from '../service/dieta.service';
import { ToastrService } from 'ngx-toastr';

import swal from 'sweetalert2';

@Component({
  selector: 'app-comedor',
  templateUrl: './comedor.component.html',
  styleUrls: ['./comedor.component.css']
})
export class ComedorComponent implements OnInit {

  usuario: Usuario;
  nombreUsuario: string;
  dietas: Dieta[];
  roles: string[];
  isAdmin = false;

  constructor(private dietaService: DietaService,
    private toastr: ToastrService,
    private router: Router) { }

  ngOnInit() {
    this.cargarDietas();
  }

  cargarDietas(): void {
    this.dietaService.getdietas().subscribe(
      data => {
        this.dietas = data;
      },
      err => {
        console.log(err);

      }
    )
  }

  inscribe(id: number) {
    this.dietaService.inscribe(id).subscribe(
      data => {
        this.router.navigate(['/actExtra']);
        swal.fire('Registrado', `El usuario ha sido registrado al comedor!`, 'success')
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

}
