export class NuevoUsuario {
  id: number;
  nombre: string;
  apellido1: string;
  apellido2: string;
  dni: string;
  calle: string;
  numCalle: string;
  telefono1: string;
  fechaNac: string;
  localidad: string;
  provincia: string;
  codigoPostal: string;
  email: string;
  curso: string;
  rolPrincipal: string;
  inComedor: string;

  nombreUsuario: string;
  password: string;

    constructor(nombre: string, apellido1: string, apellido2: string, dni: string, calle: string, numCalle: string, telefono1: string, fechaNac: string, localidad: string,
      provincia: string, codigoPostal: string, email: string, curso: string, rolPrincipal: string, inComedor:string, nombreUsuario: string, password: string){
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.calle = calle;
        this.numCalle = numCalle;
        this.telefono1 = telefono1;
        this.fechaNac = fechaNac;
        this.localidad = localidad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.email = email;
        this.curso = curso;
        this.rolPrincipal = rolPrincipal;
        this.inComedor = inComedor;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
      }
}
