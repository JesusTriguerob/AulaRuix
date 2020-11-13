export class Libro {
    id?: number;
    nombre: string;
    categoria: string;
    estado: string;
    alquilado: string;
    usuario: string;

    constructor(nombre: string, categoria: string, estado: string, alquilado: string, usuario: string) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.estado = estado;
        this.alquilado = alquilado;
        this.usuario = usuario;
    }
}
