import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Denuncia } from '../domain/model/denuncia';
import { firstValueFrom } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CreateDenunciaService {

  constructor(private http: HttpClient) { }

  create(denuncia: Denuncia): Promise<any> {
    return firstValueFrom(this.http.post<any>(`${environment.api_endpoint}/denuncias`, denuncia))
  }
}
