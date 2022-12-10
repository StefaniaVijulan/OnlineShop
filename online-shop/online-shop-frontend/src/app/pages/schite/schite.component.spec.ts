import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchiteComponent } from './schite.component';

describe('SchiteComponent', () => {
  let component: SchiteComponent;
  let fixture: ComponentFixture<SchiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SchiteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SchiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
