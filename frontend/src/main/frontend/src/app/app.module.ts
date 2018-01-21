import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
// material
import {
  MatButtonModule, MatCardModule, MatCheckboxModule, MatExpansionModule, MatFormFieldModule, MatGridListModule, MatIconModule,
  MatInputModule, MatListModule,
  MatMenuModule,
  MatProgressSpinnerModule,
  MatSelectModule,
  MatSidenavModule, MatSnackBarModule, MatTableModule, MatToolbarModule
} from '@angular/material';

import {RouterModule} from '@angular/router';
import {QuestionListComponent} from './question/question-list/question-list.component';
// application
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {UploadComponent} from './upload/upload.component';
import {UserGuardService} from 'app/shared/user-guard.service'
/* shared*/
import {NotifierService} from './shared/simple-notifier.service';
import {HTTP_INTERCEPTORS} from '@angular/common/http'
import {ROUTES_CONFIG} from './config/app.routes.config';
/* boostrap*/
// import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {CookieService} from 'ngx-cookie-service';
import {JWTInterceptor} from './shared/JWTInterceptor.http';
import {QuestionnaireListComponent} from './questionnaire/questionnaire-list/questionnaire-list.component';
import {QuestionnaireDetailComponent} from './questionnaire/questionnaire-detail/questionnaire-detail.component';
import {ApiModule} from './api/api.module';
import {FlexLayoutModule} from '@angular/flex-layout';

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
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatSidenavModule,
    MatSnackBarModule,
    MatTableModule,
    MatToolbarModule,
    MatExpansionModule,
    ReactiveFormsModule,
    RouterModule,
    RouterModule.forRoot(ROUTES_CONFIG),
    ApiModule.forRoot(),
    FlexLayoutModule
  ],
  providers: [NotifierService, UserGuardService, CookieService,
    {provide: HTTP_INTERCEPTORS, useClass: JWTInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
