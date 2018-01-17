import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {QuestionnaireListComponent} from './questionnaire-list.component';
import {
  MatCardModule, MatFormFieldModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatProgressSpinnerModule,
  MatSelectModule, MatSidenavModule, MatSnackBarModule
} from '@angular/material';
import {RouterTestingModule} from '@angular/router/testing';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {QuestionnaireService} from '../../api/services/questionnaire.service';
import {NotifierService} from '../../shared/simple-notifier.service';
import {CategoryService} from '../../api/services/category.service';
import {Observable} from 'rxjs/Rx';
import {Category} from '../../api/model/category.model';
import {Questionnaire} from '../../api/model/questionnaire.model';

describe('QuestionnaireListComponent', () => {
  let component: QuestionnaireListComponent;
  let fixture: ComponentFixture<QuestionnaireListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MatProgressSpinnerModule, MatMenuModule, MatIconModule, MatListModule, MatSidenavModule, MatFormFieldModule, MatCardModule,
        MatSelectModule, ReactiveFormsModule, FormsModule, MatSnackBarModule, MatInputModule,
        HttpClientModule, BrowserAnimationsModule, RouterTestingModule],
      declarations: [QuestionnaireListComponent],
      providers: [ NotifierService,
        {provide: CategoryService, useClass: MockCategoryService},
        {provide: QuestionnaireService, useClass: MockQuestionnaireService}
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionnaireListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

});

class MockQuestionnaireService {
  public getQuestionnaires(): Observable<Questionnaire[]> {
    const questionnaire = new Questionnaire();
    questionnaire.id = 1;
    return Observable.of([questionnaire]);
  }
};


class MockCategoryService {
  public getCategories(): Observable<Category[]> {

    const category = new Category();
    category.id = '1';
    return Observable.of([category]);

  }
};
