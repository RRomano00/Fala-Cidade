import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserCredentialDto } from '../../domain/dto/user-credential-dto';
import { Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly apiUrl = `${environment.authentication_api_endpoint}/user`;

  constructor(private http: HttpClient) { }

  authenticate(credentials: UserCredentialDto): Observable<UserCredentialDto> {
    console.log('autenticando o usuario...');

    return this.http.get<UserCredentialDto[]>(
      `${this.apiUrl}?email=${credentials.email}&password=${credentials.password}`
    ).pipe(
      map(users => {
        if (users.length > 0) {
          return users[0];
        } else {
          throw new Error('Credenciais inválidas');
        }
      })
    );
  }

  isAuthenticated(): boolean {
    return localStorage.getItem('email') != null;
  }

  addDataToLocalStorage(user: UserCredentialDto) {
    console.log('adicionando dados no cache...');
    localStorage.setItem('email', user.email);
    localStorage.setItem('password', user.password);
  }

  logout() {
    localStorage.clear();
  }
}
