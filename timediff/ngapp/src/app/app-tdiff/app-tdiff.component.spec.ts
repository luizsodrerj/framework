import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppTdiffComponent } from './app-tdiff.component';

describe('AppTdiffComponent', () => {
  let component: AppTdiffComponent;
  let fixture: ComponentFixture<AppTdiffComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppTdiffComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppTdiffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
