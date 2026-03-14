import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContatosForm } from './contatos-form';

describe('ContatosForm', () => {
  let component: ContatosForm;
  let fixture: ComponentFixture<ContatosForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContatosForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContatosForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
