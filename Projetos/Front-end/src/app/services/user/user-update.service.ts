import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { UpdatePasswordDto } from '../../domain/dto/user-update-dto';

@Injectable({
  providedIn: 'root'
})
export class UserUpdateService {

  constructor(private http: HttpClient) { }


  updatePassword(data: UpdatePasswordDto): Observable<UpdatePasswordDto> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = {
      id: data.id,
      oldPassword: data.oldPassword,
      newPassword: data.newPassword
    };
    console.log(body)

    return this.http.put<UpdatePasswordDto>(`${environment.authentication_api_endpoint}/user/update-password`, body, { headers });
  }
}
