import { Component, OnInit } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule, MatSelectionList } from '@angular/material/list';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatExpansionModule } from '@angular/material/expansion';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CreateDenunciaService } from '../../../services/create-denuncia.service';
import { UserInfoDto } from '../../../domain/dto/user-info.dto';
import { CommonModule } from '@angular/common';
import { MatFormField, MatInputModule } from "@angular/material/input";
import { MatSelectModule } from '@angular/material/select';
import { ToastrService } from 'ngx-toastr';
import { Report } from '../../../domain/model/denuncia';



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
    CommonModule,
    MatInputModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
  ],
  templateUrl: './create-denuncia.component.html',
  styleUrl: './create-denuncia.component.css'
})
export class CreateDenunciaComponent implements OnInit {

  form!: FormGroup
  user!: UserInfoDto | null
  reportMinLength: number = 5;


  constructor(private createService: CreateDenunciaService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastr: ToastrService

  ) {
    this.initializeForm();
    this.loadUser();
  }

  ngOnInit(): void { }

  initializeForm() {
    this.form = this.formBuilder.group({
      city: [{ value: "Santa Rita do Sapucaí", disabled: true }, [Validators.required, Validators.minLength(3)]],
      neighborhood: ['', [Validators.required,]],
      street: ['', [Validators.required]],
      number: ['', []],
      description: ['', [Validators.required, Validators.minLength(this.reportMinLength)]],
      type: ['', [Validators.required]],
      anonimo: [false]
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

    const isAnonimo = this.form.controls['anonimo'].value;

    if (!this.user) {
      alert("Você precisa estar logado para criar uma denúncia.");
      this.router.navigate(['/login']);
      return;
    }

    let denuncia: Report = {
      city: this.form.controls['city'].value,
      neighborhood: this.form.controls['neighborhood'].value,
      street: this.form.controls['street'].value,
      number: this.form.controls['number'].value,
      description: this.form.controls['description'].value,
      type: this.form.controls['type'].value,
      status: "PENDENTE",
      email: null,
      fullname: 'Anônimo'
    };

    if (!isAnonimo && this.user) {
      denuncia.email = this.user.email;
      denuncia.fullname = this.user.fullname;
    }


    try {
      await this.createService.create(denuncia);
      console.log(denuncia)
      this.router.navigate(['/denuncia/list']);
      this.toastr.success('Denúncia criada com sucesso')
    } catch (error) {
      this.toastr.error('Não foi possível criar denúncia. Tente novamente')
    }

  }


}
