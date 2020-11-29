import { Usuario } from '../models/usuario';

export class Dieta {
    id?: number;
    nombreDieta: string;
    usuarios: Usuario[];

    constructor(nombreDieta: string, usuarios: Usuario[]) {
      this.nombreDieta = nombreDieta;
      this.usuarios = usuarios;
    }

}
