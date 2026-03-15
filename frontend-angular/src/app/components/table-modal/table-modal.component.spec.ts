import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableModalComponent } from './table-modal.component';

describe('TableModalComponent', () => {
  let component: TableModalComponent;
  let fixture: ComponentFixture<TableModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TableModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
