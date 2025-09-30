import { Component, OnInit } from '@angular/core';
import { ReadDenunciaService } from '../../../services/read-denuncia.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Report } from '../../../domain/model/denuncia';
import { MatDivider } from "@angular/material/divider";


@Component({
  selector: 'app-statistics',
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,
    MatButtonModule,
    MatDivider,
    MatDivider
  ],
  templateUrl: './statistics.component.html',
  styleUrl: './statistics.component.css'
})
export class StatisticsComponent implements OnInit {

  public TipoDenunciaColors: { [key: string]: string } = {
    BURACO_NA_RUA_OU_CALCADA: '#fa9999ff',
    POSTE_COM_LUZ_QUEIMADA: '#fce899ff',
    LIXO_ACUMULADO_OU_TERRENO_SUJO: '#9fe2a8ff',
    SINALIZACAO_OU_SEMAFORO_COM_DEFEITO: '#9cc4fcff',
    PROBLEMAS_EM_PRACAS_E_PARQUES: '#f8a2d7ff',
    FALHAS_NO_TRANSPORTE_PUBLICO: '#f7d69bff',
    PROBLEMAS_EM_POSTO_DE_SAUDE_OU_ESCOLA: '#9e7dacff',
    SOM_ALTO_OU_PERTURBACAO_DO_SOSSEGO: '#FF5733',
    OBRA_IRREGULAR_OU_IMOVEL_ABANDONADO: '#2ECC71',
    MAUS_TRATOS_AOS_ANIMAIS: '#FF0000',
    PESSOA_PRECISANDO_DE_AJUDA: '#3498DB',
    OUTROS_PROBLEMAS: '#95A5A6'
  };

  denuncias: Report[] = [];

  constructor(private readDenunciaService: ReadDenunciaService) { }

  async ngOnInit() {
    await this.loadDenuncias();
  }

  async loadDenuncias() {
    const denunciaList: Report[] = await this.readDenunciaService.findAll();
    this.denuncias = denunciaList || [];
  }

  get totalDenuncias(): number {
    return this.denuncias.length;
  }

  get abertas(): number {
    return this.denuncias.filter(d => d.status === 'PENDENTE').length;
  }

  get concluidas(): number {
    return this.denuncias.filter(d => d.status === 'ATENDIDA').length;
  }

  get denunciasPorTipo(): { tipo: string; quantidade: number }[] {
    const map: { [key: string]: number } = {};

    this.denuncias.forEach(d => {
      const tipo = d.type ?? 'DESCONHECIDO';
      map[tipo] = (map[tipo] || 0) + 1;
    });

    return Object.keys(map).map(tipo => ({
      tipo: tipo.replace('_', ' '),
      quantidade: map[tipo]
    }));
  }


}

