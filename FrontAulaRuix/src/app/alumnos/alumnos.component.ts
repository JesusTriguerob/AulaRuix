import { Component, OnInit } from '@angular/core';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent implements OnInit {

usuarios: Usuario[];
roles: string[];
isAdmin = false;
nombreUsuario: string;

  constructor(private usuarioService: UsuarioService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
  this.cargarUsuarios();
  this.nombreUsuario = this.tokenService.getUserName();
  this.roles = this.tokenService.getAuthorities();
  this.roles.forEach(rol => {
    if (rol === 'ROLE_ADMIN') {
      this.isAdmin = true;
    }
  });
  }

cargarUsuarios(): void {
  this.usuarioService.getUsuarios().subscribe(
    data => {
      this.usuarios = data;
    },
    err => {
      console.log(err);

    }
  )
}

borrar(id: number) {
  this.usuarioService.delete(id).subscribe(
    data => {
      this.toastr.success('Usuario Eliminado', 'OK', {
        timeOut: 3000, positionClass: 'toast-top-center'
      });
      this.cargarUsuarios();
    },
    err => {
      this.toastr.error(err.error.mensaje, 'Fail', {
        timeOut: 3000, positionClass: 'toast-top-center',
      });
    }
  );
}

}
