import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';
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
import { MatInputModule } from '@angular/material/input';
import { UserCreateService } from '../../../services/user/user-create.service';
import { UserRole } from '../../../domain/model/user-role';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-sign-up',
  imports: [
    RouterOutlet,
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
    ReactiveFormsModule
  ],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent implements OnInit {



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
      role: UserRole.USER
    };

    this.createUser.create(user).subscribe({
      next: (res) => {
        console.log('Usuário criado no backend:', res);
        this.toastr.success("Usuário criado com sucesso!")
        this.form.reset();
        this.router.navigate(['account/sign-in'])
      },
      error: (err) => {
        console.error('Erro ao criar usuário:', err);
        this.toastr.error('Erro ao criar usuário!')
      }
    });
  }

}
