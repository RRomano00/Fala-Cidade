import { Component, OnInit } from '@angular/core';
import { ReadDenunciaService } from '../../../services/read-denuncia.service';
import { Denuncia } from '../../../domain/model/denuncia';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-listar-denuncia',
  imports: [
    CommonModule
  ],
  templateUrl: './listar-denuncia.component.html',
  styleUrl: './listar-denuncia.component.css'
})
export class ListarDenunciaComponent implements OnInit {

  constructor(private readDenunciaService: ReadDenunciaService) { }

  denuncias: Denuncia[] = []

  ngOnInit(): void {
    this.loadDenuncias();
  }


  async loadDenuncias() {
    console.log('preparando para obter usuários');

    let denunciaList = await this.readDenunciaService.findAll();

    if (denunciaList == null) {
      console.log('nenhum usuario encontrado');
      return;
    }
    console.log(denunciaList)

    this.denuncias = denunciaList;

    console.log('usuários obtidos com sucesso');
  }

}
