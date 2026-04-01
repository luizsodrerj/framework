import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadContatoForm } from './cad-contato-form';

describe('CadContatoForm', () => {
  let component: CadContatoForm;
  let fixture: ComponentFixture<CadContatoForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadContatoForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadContatoForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
