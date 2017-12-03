import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
// material
import {
  MdButtonModule,
  MdCheckboxModule,
  MdIconModule,
  MdInputModule,
  MdMenuModule,
  MdSelectModule,
  MdSnackBarModule,
  MdToolbarModule
} from '@angular/material';
import {RouterModule} from '@angular/router';
import {QuestionComponent} from './question/question/question.component';
// application
import {AppComponent} from './app.component';
import {QuestionService} from './question/question/question.service';
import {HomeComponent} from './home/home.component';
import {UploadComponent} from './upload/upload.component';
import {UserService} from 'app/auth/user.service'
import {UserGuardService} from 'app/auth/user-guard.service'
/* shared*/
import {NotifierService} from './shared/simple-notifier.service';
import {HttpModule} from '@angular/http';
import {HTTP_PROVIDER} from './shared/custom.http.provider';
import {ROUTES_CONFIG} from './config/app.routes.config';
/* boostrap*/
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';

@NgModule({
  declarations: [
    AppComponent,
    QuestionComponent,
    HomeComponent,
    UploadComponent,

  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    MdButtonModule,
    MdCheckboxModule,
    MdToolbarModule,
    MdSelectModule,
    MdIconModule,
    MdInputModule,
    MdMenuModule,
    MdSnackBarModule,
    RouterModule,
    RouterModule.forRoot(ROUTES_CONFIG)
  ],
  providers: [QuestionService, NotifierService, HTTP_PROVIDER, UserService, UserGuardService, CookieService ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
