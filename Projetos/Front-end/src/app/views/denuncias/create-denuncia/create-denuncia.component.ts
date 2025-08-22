import { Component, OnInit } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatExpansionModule } from '@angular/material/expansion';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CreateDenunciaService } from '../../../services/create-denuncia.service';


@Component({
  selector: 'app-denuncia',
  imports: [
    RouterModule,
    MatToolbarModule,
    FormsModule,
    MatButtonModule,
    MatSidenavModule,
    MatMenuModule,
    MatIconModule,
    MatListModule,
    MatTooltipModule,
    MatExpansionModule,
    FontAwesomeModule,
    ReactiveFormsModule
  ],
  templateUrl: './create-denuncia.component.html',
  styleUrl: './create-denuncia.component.css'
})
export class CreateDenunciaComponent implements OnInit {

  form!: FormGroup

  constructor(private createService: CreateDenunciaService,
    private formBuilder: FormBuilder,
    private router: Router

  ) { this.initializeForm() }

  ngOnInit(): void { }

  initializeForm() {
    this.form = this.formBuilder.group({
      cidade: ['', [Validators.required, Validators.minLength(2)]],
      bairro: ['', [Validators.required, Validators.minLength(1)]],
      rua: ['', [Validators.required, Validators.minLength(1)]],
      numero: ['', [Validators.required, Validators.minLength(1)]],
      conteudo: ['', [Validators.required, Validators.minLength(10)]],
    })
  }

  async create() {

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    let denuncia = {
      cidade: this.form.controls['cidade'].value,
      bairro: this.form.controls['bairro'].value,
      rua: this.form.controls['rua'].value,
      numero: this.form.controls['numero'].value,
      descricao: this.form.controls['descricao'].value,
      status: "Não atendida"
    }
    await this.createService.create(denuncia)
    this.router.navigate(['/listar-denuncia'])
  }

}
