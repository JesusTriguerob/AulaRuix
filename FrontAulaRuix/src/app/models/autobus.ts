import { Usuario } from '../models/usuario';

export class Autobus {
    id?: number;
    usuarios: Usuario[];
    capacidadAutobus: number;

    constructor(usuarios: Usuario[], capacidadAutobus: number) {
      this.usuarios = usuarios;
      this.capacidadAutobus = capacidadAutobus;
    }

}
