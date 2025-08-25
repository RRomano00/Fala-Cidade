import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatExpansionModule } from '@angular/material/expansion';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatInputModule } from '@angular/material/input';
import { UserCredentialDto } from '../../../domain/dto/user-credential-dto';
import { AuthenticationService } from '../../../services/security/authentication.service';


@Component({
  selector: 'app-sign-in',
  imports: [
    RouterOutlet,
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
    MatInputModule,
    RouterModule
  ],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {

  email = new FormControl(null);
  password = new FormControl(null, [
    Validators.minLength(2),
    Validators.maxLength(4),
  ]);

  isLoginIncorrect: boolean = false;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
  ) {
    console.log("sign-in constructor")
  }

  ngOnInit(): void {
    console.log("sign-in ngOninit");
    this.isLoginIncorrect = false;

    this.loginIfCredentialIsValid();
  }

  loginIfCredentialIsValid() {
    console.log('verificando as credenciais...');
    if (this.authenticationService.isAuthenticated()) {
      console.log('credenciais validas, navegando para tela principal');
      this.router.navigate(['']);
      return;
    }
    console.log('credenciais invalidas ou nao existem no cache');
  }

  validateFields() {
    return this.email.valid && this.password.valid;
  }

  login() {
    console.log('botao de login clicado');

    let credentials: UserCredentialDto = {
      email: this.email.value!,
      password: this.password.value!,
      fullname: ''
    }

    this.authenticationService.authenticate(credentials)
      .subscribe({
        next: (user: UserCredentialDto) => {
          console.log("Login bem sucedido:", user);

          this.authenticationService.addDataToLocalStorage(user);

          this.router.navigate(['']);
        },
        error: (err) => {
          console.error('Login falhou:', err.message);
          this.isLoginIncorrect = true;
        }
      });
  }


}
