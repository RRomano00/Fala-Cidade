import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarDenunciaComponent } from './listar-denuncia.component';

describe('ListarDenunciaComponent', () => {
  let component: ListarDenunciaComponent;
  let fixture: ComponentFixture<ListarDenunciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarDenunciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarDenunciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
