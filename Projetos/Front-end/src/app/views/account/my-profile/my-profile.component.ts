import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { UserReadService } from '../../../services/user/user-read.service';
import { AuthenticationService } from '../../../services/security/authentication.service';
import { FormsModule } from "@angular/forms";
import { User } from '../../../domain/model/user';

@Component({
  selector: 'app-my-profile',
  imports: [
    RouterOutlet,
    FormsModule
  ],
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.css'
})
export class MyProfileComponent {

  user: User = { fullname: '', email: '', password: '', role: '' };

  ngOnInit(): void {
    const fullname = localStorage.getItem('fullname') || '';
    const email = localStorage.getItem('email') || '';

    this.user.fullname = fullname;
    this.user.email = email;
  }
}
