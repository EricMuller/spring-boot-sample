import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
// material
import {MdButtonModule, MdCheckboxModule} from '@angular/material';
import {MdToolbarModule} from '@angular/material';
import {MdIconModule, MdMenuModule, MdInputModule} from '@angular/material';
import {RouterModule} from '@angular/router';
import {QuestionComponent} from './question/question/question.component';
import {MdSelectModule} from '@angular/material';

import {AppComponent} from './app.component';
import {QuestionService} from './question/question.service';
import { HomeComponent } from './home/home.component';
import { UploadComponent } from './upload/upload.component'

export const ROUTES_CONFIG = [
  {
    path: '', component: HomeComponent, pathMatch: 'full'
  },
  { path: 'question', component: QuestionComponent},
  { path: 'upload', component: UploadComponent},

]

@NgModule({
  declarations: [
    AppComponent,
    QuestionComponent,
    HomeComponent,
    UploadComponent
  ],
  imports: [
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
    RouterModule,
    RouterModule.forRoot(ROUTES_CONFIG),
  ],
  providers: [QuestionService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
