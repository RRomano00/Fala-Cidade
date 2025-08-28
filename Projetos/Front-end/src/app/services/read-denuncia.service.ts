import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { environment } from '../../environments/environment';
import { Report } from '../domain/model/denuncia';

@Injectable({
  providedIn: 'root'
})
export class ReadDenunciaService {

  constructor(private http: HttpClient) { }

  findAll(): Promise<any> {

    return firstValueFrom(this.http.get<any>(`${environment.api_endpoint}/report`));
  }

  findById(id: string): Promise<Report> {
    return firstValueFrom(this.http.get<any>(`${environment.api_endpoint}/report/${id}`));
  }
}
