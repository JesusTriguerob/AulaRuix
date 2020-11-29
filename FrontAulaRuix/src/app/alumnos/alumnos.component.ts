import { Component, OnInit } from '@angular/core';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';
import swal from'sweetalert2';

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
isProf = false;

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
    if (rol === 'ROLE_PROF') {
      this.isProf = true;
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
    swal.fire({
    title: 'Estas seguro?',
    text: "¡Una vez borrado no hay vuelta atras!",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Sí, borrar'
  }).then((result) => {
    if (result.value) {
      this.usuarioService.delete(id).subscribe(
        response => {
          this.cargarUsuarios();
          swal.fire(
            'Borrado!',
            'El usuario a sido borrado con éxito',
            'success'
          )
        }
      )
    }
  })
}

}
