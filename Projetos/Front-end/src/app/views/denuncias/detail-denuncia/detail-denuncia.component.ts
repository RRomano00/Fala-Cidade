import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterModule, RouterOutlet } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ReadDenunciaService } from '../../../services/read-denuncia.service';
import { MatIconModule } from '@angular/material/icon';
import { Report } from '../../../domain/model/denuncia';
import { CommonModule } from '@angular/common';
import { DenunciaEditService } from '../../../services/denuncia-edit.service';

@Component({
  selector: 'app-detail-denuncia',
  imports: [
    RouterOutlet,
    RouterModule,
    FontAwesomeModule,
    MatIconModule,
    CommonModule
  ],
  templateUrl: './detail-denuncia.component.html',
  styleUrl: './detail-denuncia.component.css'
})
export class DetailDenunciaComponent {
  denuncia!: Report;

  denunciaId: string = '-1';
  role: string = '';


  constructor(
    private router: Router,
    private readDenunciaService: ReadDenunciaService,
    private route: ActivatedRoute,
    private edit: DenunciaEditService

  ) { }

  ngOnInit(): void {
    let denunciaId = this.route.snapshot.paramMap.get('id');

    this.denunciaId = denunciaId!

    this.loadDenunciaById(denunciaId!);
    console.log(this.denuncia)
    this.role = localStorage.getItem('role') || '';
  }

  async loadDenunciaById(denunciaId: string) {
    try {
      this.denuncia = await this.readDenunciaService.findById(denunciaId);
    } catch (error) {
    }
  }


  async updateStatusProgress(newStatus: string) {
    try {
      this.denuncia.status = newStatus;
      await this.edit.updateToInProgress(this.denunciaId);
    } catch (error) {
    }
  }

  async updateStatusConclude(newStatus: string) {
    try {
      this.denuncia.status = newStatus;
      await this.edit.updateToConclude(this.denunciaId);
    } catch (error) {
    }
  }


}
