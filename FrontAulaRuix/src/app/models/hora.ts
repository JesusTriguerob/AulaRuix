export class Hora {
  id?: number;
  hora: number;
  reservado: number;
  usuarioReserva: string;
  nombreZona: string;


  constructor(hora: number, reservado: number, usuarioReserva: string, nombreZona: string) {
    this.hora = hora;
    this.reservado = reservado;
    this.usuarioReserva = usuarioReserva;
    this.nombreZona = nombreZona;

  }
}
