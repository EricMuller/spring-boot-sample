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
/* shared*/
import {NotifierService} from './shared/simple-notifier.service';
import {HttpModule} from '@angular/http';
import {HTTP_PROVIDER} from './shared/custom.http.provider';
import {ROUTES_CONFIG} from './config/app.routes.config';
import {UserService} from './shared/user.service';
/* boostrap*/
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


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
  providers: [QuestionService, NotifierService, HTTP_PROVIDER, UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
