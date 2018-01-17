import { TestBed, inject } from '@angular/core/testing';

import { QuestionnaireService } from './questionnaire.service';
import {HttpClientModule} from '@angular/common/http';

describe('QuestionnaireService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [QuestionnaireService]
    });
  });

  it('should be created', inject([QuestionnaireService], (service: QuestionnaireService) => {
    expect(service).toBeTruthy();
  }));
});
