import { Component, OnInit } from '@angular/core';
import { Usuario } from '../models/usuario';
import { AutobusService } from '../service/autobus.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

import swal from 'sweetalert2';

@Component({
  selector: 'app-autobus',
  templateUrl: './autobus.component.html',
  styleUrls: ['./autobus.component.css']
})
export class AutobusComponent implements OnInit {

  usuario: Usuario;
  nombreUsuario: string;
  usuarios: Usuario[];
  roles: string[];
  isAdmin = false;

  constructor(private autobusService: AutobusService,
    private toastr: ToastrService,
    private router: Router) { }

  ngOnInit() {
  }
}
