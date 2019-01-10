import { TestBed } from '@angular/core/testing';

import { NewsappService } from './newsapp.service';

describe('NewsappService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NewsappService = TestBed.get(NewsappService);
    expect(service).toBeTruthy();
  });
});
