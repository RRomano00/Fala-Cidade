import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DenunciaComponent } from './create-denuncia.component';

describe('DenunciaComponent', () => {
  let component: DenunciaComponent;
  let fixture: ComponentFixture<DenunciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DenunciaComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(DenunciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
