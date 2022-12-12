import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchiteAsignateComponent } from './schite-asignate.component';

describe('SchiteAsignateComponent', () => {
  let component: SchiteAsignateComponent;
  let fixture: ComponentFixture<SchiteAsignateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SchiteAsignateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SchiteAsignateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
