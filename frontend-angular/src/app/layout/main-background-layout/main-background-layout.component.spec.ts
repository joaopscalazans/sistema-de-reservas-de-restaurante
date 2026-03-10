import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainBackgroundLayoutComponent } from './main-background-layout.component';

describe('MainBackgroundLayoutComponent', () => {
  let component: MainBackgroundLayoutComponent;
  let fixture: ComponentFixture<MainBackgroundLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MainBackgroundLayoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MainBackgroundLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
