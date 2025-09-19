import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PasswordUpdateDto } from '../../domain/dto/user-update-dto';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserUpdateService {

  constructor(private http: HttpClient) { }


  updatePassword(data: PasswordUpdateDto): Observable<PasswordUpdateDto> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = {
      email: data.email,
      currentPassword: data.oldPassword,
      newPassword: data.newPassword
    };

    return this.http.put<PasswordUpdateDto>(`${environment.authentication_api_endpoint}/user/update-password`, body, { headers });
  }
}
