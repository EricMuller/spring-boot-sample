import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {QuestionnaireService} from './services/questionnaire.service';
import {QuestionService} from './services/question.service';
import {UserService} from './services/user.service';
import {CategoryService} from './services/category.service';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule, HttpClientModule
  ],
  declarations: [],
  providers: [QuestionnaireService, QuestionService, UserService, CategoryService]
})
export class ApiModule {

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ApiModule,
      providers: [QuestionnaireService, QuestionService, UserService]
    };
  }
}
