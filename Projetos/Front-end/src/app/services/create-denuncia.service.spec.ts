import { TestBed } from '@angular/core/testing';

import { CreateDenunciaService } from './create-denuncia.service';

describe('CreateDenunciaService', () => {
  let service: CreateDenunciaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateDenunciaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
