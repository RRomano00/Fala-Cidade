import { Component, OnInit } from '@angular/core';
import { ReadDenunciaService } from '../../../services/read-denuncia.service';
import { Report } from '../../../domain/model/denuncia';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatExpansionModule } from '@angular/material/expansion';

@Component({
  selector: 'app-listar-denuncia',
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,
    MatButtonModule,
    MatExpansionModule
  ],
  templateUrl: './listar-denuncia.component.html',
  styleUrl: './listar-denuncia.component.css'
})
export class ListarDenunciaComponent implements OnInit {

  denuncias: Report[] = [];
  role: string = '';
  userEmail: string = '';
  unattended: Report[] = []
  inProgress: Report[] = []
  conclude: Report[] = []


  constructor(private readDenunciaService: ReadDenunciaService) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role') || 'USER';
    this.userEmail = localStorage.getItem('email') || '';
    this.loadDenuncias();
    this.atualizarGrupos()
  }



  async loadDenuncias() {
    let denunciaList: Report[] = await this.readDenunciaService.findAll();

    if (!denunciaList || denunciaList.length === 0) {
      console.log('nenhuma denúncia encontrada');

      this.denuncias = [];
      console.log(denunciaList)
      return;
    }

    if (this.role === 'EMPLOYEE') {
      this.denuncias = denunciaList;
    } else {
      this.denuncias = denunciaList.filter((d: Report) => d.email === this.userEmail);
    }


    console.log('Denúncias carregadas:', this.denuncias);
    this.atualizarGrupos()
  }

  atualizarGrupos() {
    this.conclude = this.denuncias.filter(d => d.status === 'ATENDIDA');
    this.inProgress = this.denuncias.filter(d => d.status === 'EM_ANDAMENTO');
    this.unattended = this.denuncias.filter(d => d.status === 'PENDENTE');
  }

}
