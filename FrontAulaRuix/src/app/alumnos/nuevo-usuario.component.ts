import { Component, OnInit } from '@angular/core';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { NuevoUsuario } from '../models/nuevo-usuario';


@Component({
  selector: 'app-nuevo-usuario',
  templateUrl: './nuevo-usuario.component.html',
  styleUrls: ['./nuevo-usuario.component.css']
})
export class NuevoUsuarioComponent implements OnInit {

  nuevoUsuario: NuevoUsuario = null;

  nombre = '';
  apellido1 = '';
  apellido2 = '';
  dni = '';
  calle = '';
  numCalle = '';
  telefono1 = '';
  fechaNac = '';
  localidad = '';
  provincia = '';
  codigoPostal = '';
  email = '';
  curso = '';
  rolPrincipal = '';
  inComedor = '';

  nombreUsuario = '';
  password = '';
  errMsj: string;
  isLogged = false;
  isAdmin = false;
  roles: string[];

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }
  }

  onRegister(): void {
    this.nuevoUsuario = new NuevoUsuario(this.nombre, this.apellido1, this.apellido2, this.dni, this.calle, this.numCalle, this.telefono1, this.fechaNac, this.localidad,
      this.provincia, this.codigoPostal, this.email, this.curso, this.rolPrincipal, this.inComedor, this.nombreUsuario, this.password);
    this.authService.nuevo(this.nuevoUsuario).subscribe(
      data => {
        this.toastr.success('Cuenta Creada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });

        this.router.navigate(['/alumnos']);
      },
      err => {
        this.errMsj = err.error.mensaje;
        this.toastr.error(this.errMsj, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        // console.log(err.error.message);
      }
    );
  }

}
