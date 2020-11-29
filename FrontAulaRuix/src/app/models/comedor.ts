import { Dieta } from '../models/dieta';

export class Comedor {
    id?: number;
    dietas: Dieta[];
    capacidadComedor: number;

    constructor(dietas: Dieta[], capacidadComedor: number) {
      this.dietas = dietas;
      this.capacidadComedor = capacidadComedor;
    }

}
