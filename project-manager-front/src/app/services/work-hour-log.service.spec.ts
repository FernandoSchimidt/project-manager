import { TestBed } from '@angular/core/testing';

import { WorkHourLogService } from './work-hour-log.service';

describe('WorkHourLogService', () => {
  let service: WorkHourLogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkHourLogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
