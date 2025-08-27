import { Component, OnInit } from '@angular/core';
import { ReadDenunciaService } from '../../../services/read-denuncia.service';
import { Report } from '../../../domain/model/denuncia';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-listar-denuncia',
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,
    MatButtonModule
  ],
  templateUrl: './listar-denuncia.component.html',
  styleUrl: './listar-denuncia.component.css'
})
export class ListarDenunciaComponent implements OnInit {

  denuncias: Report[] = [];
  role: string = '';
  userEmail: string = '';

  constructor(private readDenunciaService: ReadDenunciaService) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role') || 'USER';
    this.userEmail = localStorage.getItem('email') || '';
    this.loadDenuncias();
  }

  async loadDenuncias() {
    let denunciaList: Report[] = await this.readDenunciaService.findAll();

    if (!denunciaList || denunciaList.length === 0) {
      console.log('nenhuma denúncia encontrada');
      this.denuncias = [];
      return;
    }

    if (this.role === 'EMPLOYEE') {
      this.denuncias = denunciaList;
    } else {
      this.denuncias = denunciaList.filter((d: Report) => d.email === this.userEmail);
    }

    console.log('Denúncias carregadas:', this.denuncias);
  }

}
