import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { environment } from '../../../environments/environment';
import { User } from '../../domain/model/user';

@Injectable({
  providedIn: 'root'
})
export class UserReadService {

  constructor(private http: HttpClient) { }

  findById(id: string): Promise<User> {
    return firstValueFrom(this.http.get<any>(`${environment.api_endpoint}/user/${id}`));
  }


}
