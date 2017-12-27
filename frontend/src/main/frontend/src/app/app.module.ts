import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
// material
import {
  MatButtonModule,
  MatCheckboxModule,
  MatIconModule,
  MatInputModule,
  MatMenuModule,
  MatSelectModule,
  MatSnackBarModule,
  MatToolbarModule,
  MatListModule,
  MatTableModule
} from '@angular/material';

import {RouterModule} from '@angular/router';
import {QuestionListComponent} from './question/question-list/question-list.component';
// application
import {AppComponent} from './app.component';
import {QuestionService} from './api/api/question.service';
import {HomeComponent} from './home/home.component';
import {UploadComponent} from './upload/upload.component';
import {UserService} from 'app/api/api/user.service'
import {UserGuardService} from 'app/shared/user-guard.service'
/* shared*/
import {NotifierService} from './shared/simple-notifier.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http'
import {ROUTES_CONFIG} from './config/app.routes.config';
/* boostrap*/
// import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {CookieService} from 'ngx-cookie-service';
import {JWTInterceptor} from './shared/JWTInterceptor.http';
import { QuestionnaireListComponent } from './questionnaire/questionnaire-list/questionnaire-list.component';
import { QuestionnaireDetailComponent } from './questionnaire/questionnaire-detail/questionnaire-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    QuestionListComponent,
    HomeComponent,
    UploadComponent,
    QuestionnaireListComponent,
    QuestionnaireDetailComponent,

  ],
  imports: [
   // NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatSelectModule,
    MatIconModule,
    MatInputModule,
    MatMenuModule,
    MatSnackBarModule,
    MatListModule,
    MatTableModule,
    RouterModule,
    RouterModule.forRoot(ROUTES_CONFIG)
  ],
  providers: [QuestionService, NotifierService, UserService, UserGuardService, CookieService,
    {provide: HTTP_INTERCEPTORS, useClass: JWTInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
