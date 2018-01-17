import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {QuestionListComponent} from './question-list.component';
import {MatIconModule, MatListModule, MatSnackBarModule, MatTableModule} from '@angular/material';
import {HttpClientModule} from '@angular/common/http';
import {QuestionService} from '../../api';
import {NotifierService} from '../../shared/simple-notifier.service';
import {RouterTestingModule} from '@angular/router/testing';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

describe('QuestionListComponent', () => {
  let component: QuestionListComponent;
  let fixture: ComponentFixture<QuestionListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MatTableModule, MatIconModule, MatListModule, MatSnackBarModule,
        HttpClientModule, BrowserAnimationsModule, RouterTestingModule
      ],
      declarations: [QuestionListComponent],
      providers: [QuestionService, NotifierService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  })
  ;
});
