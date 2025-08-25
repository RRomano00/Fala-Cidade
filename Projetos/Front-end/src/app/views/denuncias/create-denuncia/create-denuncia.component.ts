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
import { UserInfoDto } from '../../../domain/dto/user-info.dto';
import { CommonModule } from '@angular/common';


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
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './create-denuncia.component.html',
  styleUrl: './create-denuncia.component.css'
})
export class CreateDenunciaComponent implements OnInit {

  form!: FormGroup
  user!: UserInfoDto | null

  constructor(private createService: CreateDenunciaService,
    private formBuilder: FormBuilder,
    private router: Router

  ) {
    this.initializeForm();
    this.loadUser();
  }

  ngOnInit(): void { }

  initializeForm() {
    this.form = this.formBuilder.group({
      cidade: ['', [Validators.required, Validators.minLength(3)]],
      bairro: ['', [Validators.required,]],
      rua: ['', [Validators.required]],
      numero: ['', [Validators.required]],
      descricao: ['', [Validators.required, Validators.minLength(10)]],
    })
  }

  loadUser() {
    const email = localStorage.getItem('email');
    const fullname = localStorage.getItem('fullname');
    if (email) {
      this.user = { email, fullname: fullname ?? '' };
    } else {
      this.user = null;
    }
  }
  async create() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    if (!this.user) {
      alert("Você precisa estar logado para criar uma denúncia.");
      this.router.navigate(['/login']);
      return;
    }

    let denuncia = {
      cidade: this.form.controls['cidade'].value,
      bairro: this.form.controls['bairro'].value,
      rua: this.form.controls['rua'].value,
      numero: this.form.controls['numero'].value,
      descricao: this.form.controls['descricao'].value,
      status: "Pendente",
      email: this.user.email,
      fullname: this.user.fullname
    };

    await this.createService.create(denuncia);
    this.router.navigate(['/listar-denuncia']);
  }



}
