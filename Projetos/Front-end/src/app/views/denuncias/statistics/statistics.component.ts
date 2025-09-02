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
    BURACO_NA_RUA_OU_CALCADA: '#FF6B6B',
    POSTE_COM_LUZ_QUEIMADA: '#FFD93D',
    LIXO_ACUMULADO_OU_TERRENO_SUJO: '#6BCB77',
    SINALIZACAO_OU_SEMAFORO_COM_DEFEITO: '#4D96FF',
    PROBLEMAS_EM_PRACAS_E_PARQUES: '#FF6EC7',
    FALHAS_NO_TRANSPORTE_PUBLICO: '#FFA500',
    PROBLEMAS_EM_POSTO_DE_SAUDE_OU_ESCOLA: '#8E44AD',
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

