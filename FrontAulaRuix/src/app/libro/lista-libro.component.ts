import { Component, OnInit } from '@angular/core';
import { Libro } from '../models/libro';
import { LibroService } from '../service/libro.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-lista-libro',
  templateUrl: './lista-libro.component.html',
  styleUrls: ['./lista-libro.component.css']
})
export class ListaLibroComponent implements OnInit {

  libros: Libro[] = [];
  roles: string[];
  isAdmin = false;
  nombreUsuario: string;

  constructor(
    private libroService: LibroService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.cargarLibros();
    this.nombreUsuario = this.tokenService.getUserName();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }

  cargarLibros(): void {
    this.libroService.lista().subscribe(
      data => {
        this.libros = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  borrar(id: number) {
    this.libroService.delete(id).subscribe(
      data => {
        this.toastr.success('Libro Eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarLibros();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

  alquilar(id: number) {
    this.libroService.alquilar(id).subscribe(
      data => {
        this.cargarLibros();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

}
