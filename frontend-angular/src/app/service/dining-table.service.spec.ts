import { TestBed } from '@angular/core/testing';

import { DiningTableService } from './dining-table.service';

describe('DiningTableService', () => {
  let service: DiningTableService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DiningTableService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
