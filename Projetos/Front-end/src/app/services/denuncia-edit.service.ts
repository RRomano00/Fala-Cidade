import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Denuncia } from '../domain/model/denuncia';
import { firstValueFrom } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DenunciaEditService {

  constructor(private http: HttpClient) { }

  async update(id: string, denuncia: Denuncia): Promise<Denuncia> {
  return firstValueFrom(
    this.http.put<Denuncia>(`${environment.api_endpoint}/denuncias/${id}`, denuncia)
  );
}
}
