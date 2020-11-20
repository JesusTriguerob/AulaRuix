import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListaLibroComponent } from './libro/lista-libro.component';
import { DetalleLibroComponent } from './libro/detalle-libro.component';
import { NuevoLibroComponent } from './libro/nuevo-libro.component';
import { EditarLibroComponent} from './libro/editar-libro.component';
import { AlumnosComponent} from './alumnos/alumnos.component';
import { FormALumnoComponent} from './alumnos/form-alumno.component';
import { InfoAlumnoComponent } from './alumnos/info-alumno.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { LibroGuardService as guard } from './guards/libro-guard.service';
import { AulaComponent} from './aula/aula.component';
import { NuevoAulaComponent } from './aula/nuevo-aula.component';




const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'lista', component: ListaLibroComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: 'detalle/:id', component: DetalleLibroComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: 'nuevo', component: NuevoLibroComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'editar/:id', component: EditarLibroComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'alumnos', component: AlumnosComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: 'formAlumno', component: FormALumnoComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'formAlumno/:id', component: FormALumnoComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user'] } },
  { path: 'infoAlumno/:id', component: InfoAlumnoComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'aula', component: AulaComponent, canActivate: [guard], data: { expectedRol:['admin', 'user']  } },
  { path: 'aula/nuevo', component: NuevoAulaComponent , canActivate: [guard], data: { expectedRol:['admin']} },
  { path: '**', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
