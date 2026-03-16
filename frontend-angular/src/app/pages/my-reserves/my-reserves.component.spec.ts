import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyReservesComponent } from './my-reserves.component';

describe('MyReservesComponent', () => {
  let component: MyReservesComponent;
  let fixture: ComponentFixture<MyReservesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyReservesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyReservesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
