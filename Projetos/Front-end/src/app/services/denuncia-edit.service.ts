import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Report } from '../domain/model/denuncia';
import { firstValueFrom } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DenunciaEditService {

  constructor(private http: HttpClient) { }

  // async update(id: string, denuncia: Report): Promise<Report> {
  //   return firstValueFrom(
  //     this.http.put<Report>(`${environment.api_endpoint}/denuncias/${id}`, denuncia)
  //   );
  // }

  async updateToInProgress(id: string): Promise<Report> {
    return firstValueFrom(
      this.http.put<Report>(`${environment.api_endpoint}/report/progress/${id}`, {})
    );
  }

  async updateToConclude(id: string): Promise<Report> {
    return firstValueFrom(
      this.http.put<Report>(`${environment.api_endpoint}/report/conclude/${id}`, {})
    );
  }
}
