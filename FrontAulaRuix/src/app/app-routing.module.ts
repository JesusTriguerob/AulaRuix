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
import { ZonaComunComponent} from './zonaComun/zonaComun.component';
import { NuevoZonaComunComponent } from './zonaComun/nuevo-zonaComun.component';
import { NuevoUsuarioComponent } from './alumnos/nuevo-usuario.component';
import { ActividadesExtraComponent } from './actividades-extra/actividades-extra.component';
import { ComedorComponent } from './actividades-extra/comedor.component';
import { AutobusComponent } from './actividades-extra/autobus.component';



const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'lista', component: ListaLibroComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user', 'profesor'] } },
  { path: 'detalle/:id', component: DetalleLibroComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user', 'profesor'] } },
  { path: 'nuevo', component: NuevoLibroComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'editar/:id', component: EditarLibroComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'alumnos', component: AlumnosComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user', 'profesor'] } },
  { path: 'formAlumno', component: FormALumnoComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'formAlumno/:id', component: FormALumnoComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user', 'profesor'] } },
  { path: 'infoAlumno/:id', component: InfoAlumnoComponent, canActivate: [guard], data: { expectedRol: ['admin', 'profesor'] } },
  { path: 'zona', component: ZonaComunComponent, canActivate: [guard], data: { expectedRol:['admin', 'user', 'profesor']  } },
  { path: 'zona/nuevo', component: NuevoZonaComunComponent , canActivate: [guard], data: { expectedRol:['admin']} },
  { path: 'usuario/nuevo', component: NuevoUsuarioComponent , canActivate: [guard], data: { expectedRol:['admin']} },
  { path: 'actExtra', component: ActividadesExtraComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user', 'profesor'] } },
  { path: 'comedor', component: ComedorComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user', 'profesor'] } },
  { path: 'autobus', component: AutobusComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user', 'profesor'] } },
  { path: '**', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
