import {HomeComponent} from '../home/home.component';
import {QuestionComponent} from '../question/question/question.component';
import {UploadComponent} from '../upload/upload.component';
import {UserGuardService} from 'app/auth/user-guard.service'

export const ROUTES_CONFIG = [
  {
    path: '', component: HomeComponent, pathMatch: 'full'
  },
  {
    path: 'questions', component: QuestionComponent, canActivate: [UserGuardService],
    children: [
      {path: 'upload', component: UploadComponent, canActivate: [UserGuardService]},
    ]
  },
  {path: 'upload', component: UploadComponent, canActivate: [UserGuardService]},
  {path: '**', redirectTo: ''}
]

