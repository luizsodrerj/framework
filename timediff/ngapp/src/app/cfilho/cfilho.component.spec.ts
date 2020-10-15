import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CfilhoComponent } from './cfilho.component';

describe('CfilhoComponent', () => {
  let component: CfilhoComponent;
  let fixture: ComponentFixture<CfilhoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CfilhoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CfilhoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
