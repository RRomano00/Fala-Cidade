import { TestBed } from '@angular/core/testing';

import { ReadDenunciaService } from './read-denuncia.service';

describe('ReadDenunciaService', () => {
  let service: ReadDenunciaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReadDenunciaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
