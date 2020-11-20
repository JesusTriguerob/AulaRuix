import { Hora } from '../models/hora';

export class Aula {
    id?: number;
    nombre: string;
    horas: Hora[];


    constructor(nombre: string, horas: Hora[]) {
        this.nombre = nombre;
        this.horas = horas;

    }
}
