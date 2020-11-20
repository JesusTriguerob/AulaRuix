export class NuevoUsuario {
    nombre: string;
    nombreUsuario: string;
    email: string;
    password: string;

    apellido1: string;
    apellido2: string;
    dni: string;
    fechaNac: string;
    localidad: string;
    constructor(nombre: string, nombreUsuario: string, email: string, password: string, apellido1: string, apellido2: string, dni: string, fechaNac: string, localidad: string) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.localidad = localidad;
    }
}
