import { Component, OnInit } from '@angular/core';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';
import { ActivatedRoute, Router } from '@angular/router';
import swal from'sweetalert2';

@Component({
  selector: 'app-form-alumno',
  templateUrl: './form-alumno.component.html',
  styleUrls: ['./form-alumno.component.css']
})
export class FormALumnoComponent implements OnInit {

  usuario: Usuario = null;
  roles: string[];
  isAdmin = false;
  nombreUsuario: string;

  constructor(private usuarioService: UsuarioService,
    private activatedRoute: ActivatedRoute,
    private tokenService: TokenService,
    private toastr: ToastrService,
    private router: Router) { }

  ngOnInit() {
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
    const id = this.activatedRoute.snapshot.params.id;
    this.usuarioService.detail(id).subscribe(
      data => {
        this.usuario = data;
        this.nombreUsuario = this.tokenService.getUserName();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Error en la busqueda de usuario.', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.volver();
      }
    );
  }

  volver(): void {
    this.router.navigate(['/alumnos']);
  }

  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.usuarioService.update(id, this.usuario).subscribe(
      data => {

        this.router.navigate(['/alumnos']);
        swal.fire('Usuario actualizado' , `El usuario  ha sido actualizado con éxito!`, 'success' )
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Error en la actualizacion', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        // this.router.navigate(['/']);
      }
    );
  }
}
