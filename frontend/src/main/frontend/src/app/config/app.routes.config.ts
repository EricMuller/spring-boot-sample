import {HomeComponent} from '../home/home.component';
import {QuestionComponent} from '../question/question/question.component';
import {UploadComponent} from '../upload/upload.component';
import {SpringGuardService} from 'app/auth/spring-guard.service'

export const ROUTES_CONFIG = [
  {
    path: '', component: HomeComponent, pathMatch: 'full'
  },
  {
    path: 'questions', component: QuestionComponent, canActivate: [SpringGuardService],
    children: [
      {path: 'upload', component: UploadComponent, canActivate: [SpringGuardService]},
    ]
  },
  {path: 'upload', component: UploadComponent, canActivate: [SpringGuardService]},
  {path: '**', redirectTo: ''}
]

