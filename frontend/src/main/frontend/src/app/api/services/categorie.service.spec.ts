import { TestBed, inject } from '@angular/core/testing';

import { CategoryService } from './category.service';
import {HttpClientModule} from '@angular/common/http';

describe('CategoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [CategoryService]
    });
  });

  it('should be created', inject([CategoryService], (service: CategoryService) => {
    expect(service).toBeTruthy();
  }));
});
