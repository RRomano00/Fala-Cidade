import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { UserCredentialDto } from '../../domain/dto/user-credential-dto';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly apiUrl = `${environment.authentication_api_endpoint}`;

  constructor(private http: HttpClient) { }

  authenticate(credentials: UserCredentialDto): Observable<UserCredentialDto> {
    console.log('autenticando o usuario...');

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { email: credentials.email, password: credentials.password };

    // Chamada POST para autenticação
    //return this.http.post<any>(`${this.apiUrl}/authenticate`, body, { headers });

    // Caso vá usar o JSON Server para simulação, descomente a linha abaixo:
    return this.http.get<any>(`${this.apiUrl}/user`);
  }

  isAuthenticated(): boolean {
    let email = localStorage.getItem('email');
    if (email != null) {
      console.log(`Usuário autenticado: ${email}`);
      return true;
    }
    return false;
  }

  addDataToLocalStorage(user: UserCredentialDto) {
    console.log('adicionando dados no cache...');
    localStorage.setItem('email', user.email);
    localStorage.setItem('password', user.password);
    localStorage.setItem('fullname', user.fullname ?? '');
    localStorage.setItem('role', user.role ?? '');
  }

  logout() {
    localStorage.clear();
    console.log('usuário deslogado');
  }
}
