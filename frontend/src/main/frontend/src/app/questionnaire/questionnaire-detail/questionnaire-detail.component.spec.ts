import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {QuestionnaireDetailComponent} from './questionnaire-detail.component';
import {MatCardModule, MatIconModule, MatListModule, MatSnackBarModule, MatToolbarModule} from '@angular/material';
import {QuestionService} from '../../api';
import {HttpClientModule} from '@angular/common/http';
import {QuestionnaireService} from '../../api/services/questionnaire.service';
import {NotifierService} from '../../shared/simple-notifier.service';
import {RouterTestingModule} from '@angular/router/testing';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

describe('QuestionnaireDetailComponent', () => {
  let component: QuestionnaireDetailComponent;
  let fixture: ComponentFixture<QuestionnaireDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [  HttpClientModule, BrowserAnimationsModule, RouterTestingModule,
        MatIconModule, MatToolbarModule, MatCardModule, MatListModule, MatSnackBarModule
      ],
      declarations: [QuestionnaireDetailComponent],
      providers: [QuestionService, QuestionnaireService, NotifierService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionnaireDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
