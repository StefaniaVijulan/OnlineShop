import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchiteleMeleComponent } from './schitele-mele.component';

describe('SchiteleMeleComponent', () => {
  let component: SchiteleMeleComponent;
  let fixture: ComponentFixture<SchiteleMeleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SchiteleMeleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SchiteleMeleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
