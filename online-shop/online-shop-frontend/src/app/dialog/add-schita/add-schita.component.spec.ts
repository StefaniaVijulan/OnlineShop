import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSchitaComponent } from './add-schita.component';

describe('AddSchitaComponent', () => {
  let component: AddSchitaComponent;
  let fixture: ComponentFixture<AddSchitaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSchitaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSchitaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
