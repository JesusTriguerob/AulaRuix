import { Component, OnInit } from '@angular/core';
import { Libro } from '../models/libro';
import { LibroService } from '../service/libro.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';
import swal from 'sweetalert2';

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
        this.libroService.delete(id).subscribe(
          response => {
            this.cargarLibros();
            swal.fire(
              'Borrado!',
              'El libro ha sido borrado con éxito',
              'success'
            )
          }
        )
      }
    })
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
