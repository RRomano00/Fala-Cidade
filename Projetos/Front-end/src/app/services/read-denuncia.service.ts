import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReadDenunciaService {

  constructor(private http: HttpClient) { }

  findAll(): Promise<any> {

    return firstValueFrom(this.http.get<any>(`${environment.api_endpoint}/denuncias`));
  }
}
