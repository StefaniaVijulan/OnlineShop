import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesignersTabComponent } from './designers-tab.component';

describe('DesignersTabComponent', () => {
  let component: DesignersTabComponent;
  let fixture: ComponentFixture<DesignersTabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DesignersTabComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DesignersTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
