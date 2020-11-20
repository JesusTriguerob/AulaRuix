import { Component, OnInit } from '@angular/core';
import { LibroService } from '../service/libro.service';
import { Libro } from '../models/libro';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nuevo-libro',
  templateUrl: './nuevo-libro.component.html',
  styleUrls: ['./nuevo-libro.component.css']
})
export class NuevoLibroComponent implements OnInit {

  nombre = '';
  categoria = '';
  estado = 0;
  alquilado = 1;
  usuario = '';
  fechaAlquiler = '';
  fechaDevolucion = '';
  vecesAlquilado = 0;

  constructor(
    private libroService: LibroService,
    private toastr: ToastrService,
    private router: Router
    ) { }

  ngOnInit() {
  }

  onCreate(): void {
    const libro = new Libro(this.nombre, this.categoria,this.estado,this.alquilado,this.usuario,this.fechaAlquiler,this.fechaDevolucion, this.vecesAlquilado);
    this.libroService.save(libro).subscribe(
      data => {
        this.toastr.success('Libro Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/lista']);
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
