import { Component, OnInit } from '@angular/core';
import { Libro } from '../models/libro';
import { LibroService } from '../service/libro.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import swal from'sweetalert2';

@Component({
  selector: 'app-editar-libro',
  templateUrl: './editar-libro.component.html',
  styleUrls: ['./editar-libro.component.css']
})
export class EditarLibroComponent implements OnInit {

  libro: Libro = null;

  constructor(
    private libroService: LibroService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params.id;
    this.libroService.detail(id).subscribe(
      data => {
        this.libro = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Error en la busqueda del libro', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }

  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.libroService.update(id, this.libro).subscribe(
      data => {
        this.router.navigate(['/lista']);
        swal.fire('Libro actualizado' , `El libro  ha sido actualizado con éxito!`, 'success' )
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
