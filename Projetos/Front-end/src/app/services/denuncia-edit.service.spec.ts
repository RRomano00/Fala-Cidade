import { TestBed } from '@angular/core/testing';

import { DenunciaEditService } from './denuncia-edit.service';

describe('DenunciaEditService', () => {
  let service: DenunciaEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DenunciaEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
