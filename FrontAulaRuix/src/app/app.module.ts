import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaLibroComponent } from './libro/lista-libro.component';
import { DetalleLibroComponent } from './libro/detalle-libro.component';
import { NuevoLibroComponent } from './libro/nuevo-libro.component';
import { EditarLibroComponent } from './libro/editar-libro.component';
import { interceptorProvider } from './interceptors/libro-interceptor.service';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// external
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { MenuComponent } from './menu/menu.component';
import { IndexComponent } from './index/index.component';
import { AlumnosComponent } from './alumnos/alumnos.component';
import { FormALumnoComponent } from './alumnos/form-alumno.component';
import { InfoAlumnoComponent } from './alumnos/info-alumno.component';
import { FooterComponent } from './footer/footer.component';
import { ZonaComunComponent } from './zonaComun/zonaComun.component';
import { NuevoZonaComunComponent } from './zonaComun/nuevo-zonaComun.component';
import { NuevoUsuarioComponent } from './alumnos/nuevo-usuario.component';
import { ActividadesExtraComponent } from './actividades-extra/actividades-extra.component';
import { ComedorComponent } from './actividades-extra/comedor.component';



@NgModule({
  declarations: [
    AppComponent,
    ListaLibroComponent,
    DetalleLibroComponent,
    NuevoLibroComponent,
    EditarLibroComponent,
    LoginComponent,
    RegistroComponent,
    MenuComponent,
    IndexComponent,
    AlumnosComponent,
    FormALumnoComponent,
    InfoAlumnoComponent,
    FooterComponent,
    ZonaComunComponent,
    NuevoZonaComunComponent,
    NuevoUsuarioComponent,
    ActividadesExtraComponent,
    ComedorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
