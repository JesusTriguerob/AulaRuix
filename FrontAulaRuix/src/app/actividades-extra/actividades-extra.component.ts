import { Component, OnInit } from '@angular/core';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';
import { TokenService } from '../service/token.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { DietaService } from '../service/dieta.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-actividades-extra',
  templateUrl: './actividades-extra.component.html',
  styleUrls: ['./actividades-extra.component.css']
})
export class ActividadesExtraComponent implements OnInit {

  usuario: Usuario;
  nombreUsuario: string;
  isComedor = false;

  constructor(private usuarioService: UsuarioService,
    private tokenService: TokenService,
    private dietaService: DietaService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.cargarInfoUser();
  }

  cargarInfoUser(): void {
    this.nombreUsuario = this.tokenService.getUserName();
    this.usuarioService.detailName(this.nombreUsuario).subscribe(
      data => {
        this.usuario = data;
        if (this.usuario.inComedor == '1') {
          this.isComedor = true;
          console.log('aaaaaaaa');
        }
      },
      err => {
        console.log(err);
      }
    );
  }

  desInscribe(): void {
    this.dietaService.desInscribe().subscribe(
      data => {
        this.router.navigate(['/actExtra']);
        swal.fire('Registrado', `El usuario ha sido eliminado del comedor!`, 'success')
        window.location.reload();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
}
