export class Libro {
    id?: number;
    nombre: string;
    categoria: string;
    estado: number;
    alquilado: number;
    usuario: string;
    fechaAlquiler: string;
    fechaDevolucion: string;
    vecesAlquilado: number;

    constructor(nombre: string, categoria: string, estado: number, alquilado: number, usuario: string, fechaAlquiler: string, fechaDevolucion: string, vecesAlquilado: number) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.estado = estado;
        this.alquilado = alquilado;
        this.usuario = usuario;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
        this.vecesAlquilado = vecesAlquilado;
    }
}
