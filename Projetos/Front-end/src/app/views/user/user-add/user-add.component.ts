import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from "@angular/material/input";
import { UserCreateService } from '../../../services/user/user-create.service';
import { ToastrService } from 'ngx-toastr';
import { Router, RouterModule } from '@angular/router';
import { UserRole } from '../../../domain/model/user-role';
import { MatSelectModule } from "@angular/material/select";
import { MatDivider } from "@angular/material/divider";
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-user-add',
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatMenuModule,
    MatIconModule,
    MatListModule,
    MatTooltipModule,
    MatExpansionModule,
    FontAwesomeModule,
    MatInputModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule
  ],
  templateUrl: './user-add.component.html',
  styleUrl: './user-add.component.css'
})
export class UserAddComponent implements OnInit {

  form!: FormGroup;
  fullnameMinLength: number = 2;
  passwordMinLength: number = 2;

  constructor(private formBuilder: FormBuilder,
    private createUser: UserCreateService,
    private toastr: ToastrService,
    private router: Router
  ) {
    this.initializeForm();
  }

  ngOnInit(): void {

  }

  initializeForm() {
    console.log('formulario de sign-up sendo inicializado');
    this.form = this.formBuilder.group({
      fullname: ['', [
        Validators.required,
        Validators.minLength(this.fullnameMinLength),
      ]],
      email: ['', [
        Validators.required,
        Validators.email,
      ]],
      password: ['', [
        Validators.required,
        Validators.minLength(this.passwordMinLength),

      ]],
      repeatPassword: ['', [
        Validators.required,
        Validators.minLength(this.passwordMinLength),
      ]],
      userRole: ['', [
        Validators.required,
        Validators.minLength(this.passwordMinLength),
      ]],
    })
  }

  validateFields(): boolean {
    let isFullnameValid = this.form.controls['fullname'].valid;
    let isEmailValid = this.form.controls['email'].valid;
    let isPasswordValid = this.form.controls['password'].valid;
    let isRepeatPasswordValid = this.form.controls['repeatPassword'].valid;

    if (!this.arePasswordsValid()) {
      return false;
    }

    return isFullnameValid
      && isEmailValid
      && isPasswordValid
      && isRepeatPasswordValid;
  }

  arePasswordsValid() {
    let password = this.form.controls['password'].value;
    let repeatPassword = this.form.controls['repeatPassword'].value;


    return password === repeatPassword;
  }

  async create() {
    if (!this.arePasswordsValid()) {
      alert('As senhas não coincidem!');
      return;
    }

    let user = {
      fullname: this.form.controls['fullname'].value,
      email: this.form.controls['email'].value,
      password: this.form.controls['password'].value,
      role: this.form.controls['userRole'].value
    };

    this.createUser.create(user).subscribe({
      next: (res) => {
        this.toastr.success("Usuário criado com sucesso!")
        this.form.reset();
        this.router.navigate(['account/sign-in'])
      },
      error: (err) => {
        this.toastr.error('Erro ao criar usuário!')
      }
    });
  }
}
