import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailDenunciaComponent } from './detail-denuncia.component';

describe('DetailDenunciaComponent', () => {
  let component: DetailDenunciaComponent;
  let fixture: ComponentFixture<DetailDenunciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailDenunciaComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(DetailDenunciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
