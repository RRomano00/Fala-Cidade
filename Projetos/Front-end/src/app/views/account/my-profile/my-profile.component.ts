import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterModule, RouterOutlet } from '@angular/router';
import { UserReadService } from '../../../services/user/user-read.service';
import { AuthenticationService } from '../../../services/security/authentication.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { User } from '../../../domain/model/user';
import { UserRole } from '../../../domain/model/user-role';
import { UpdatePasswordDto } from '../../../domain/dto/user-update-dto';
import { UserUpdateService } from '../../../services/user/user-update.service';
import { ToastrService } from 'ngx-toastr';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatExpansionModule } from '@angular/material/expansion';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-my-profile',
  standalone: true,
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
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.css'
})
export class MyProfileComponent {

  user: User = { fullname: '', email: '', password: '', role: UserRole.USER };

  // ngOnInit(): void {
  //   const fullname = localStorage.getItem('fullname') || '';
  //   const email = localStorage.getItem('email') || '';

  //   this.user.fullname = fullname;
  //   this.user.email = email;
  // }
  form!: FormGroup;
  id? = localStorage.getItem('id');
  passwordMinLength = 2;

  constructor(
    private formBuilder: FormBuilder,
    private userUpdateService: UserUpdateService,
    private toastr: ToastrService,
    private router: Router
  ) {
    this.initializeForm();
  }

  ngOnInit(): void {
    const id = localStorage.getItem('id');
    const fullname = localStorage.getItem('fullname') || '';
    const email = localStorage.getItem('email') || '';

    this.user.fullname = fullname;
    this.user.email = email;
  }

  initializeForm() {
    this.form = this.formBuilder.group({
      oldPassword: ['', [Validators.required]],
      newPassword: ['', [Validators.required, Validators.minLength(this.passwordMinLength)]],
      confirmPassword: ['', [Validators.required]]
    });
  }

  validatePasswords(): boolean {
    return this.form.controls['newPassword'].value === this.form.controls['confirmPassword'].value;
  }

  save() {
    console.log()
    //if (this.form.invalid) return;

    if (!this.validatePasswords()) {
      this.toastr.error('As senhas não coincidem!');
      return;
    }

    const dto: UpdatePasswordDto = {
      id: this.id || '',
      oldPassword: this.form.controls['oldPassword'].value,
      newPassword: this.form.controls['newPassword'].value
    };
    console.log(dto)

    this.userUpdateService.updatePassword(dto).subscribe({
      next: () => {
        this.toastr.success('Senha atualizada com sucesso!');
        this.form.reset();
        this.router.navigate(['']);
      },
      error: () => {
        this.toastr.error('Erro ao atualizar senha. Verifique a senha atual.');
      }
    });
  }
}
