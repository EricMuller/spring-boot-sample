import {HomeComponent} from '../home/home.component';
import {QuestionListComponent} from '../question/question-list/question-list.component';
import {UploadComponent} from '../upload/upload.component';
import {UserGuardService} from 'app/shared/user-guard.service'
import {QuestionnaireListComponent} from '../questionnaire/questionnaire-list/questionnaire-list.component';
import {QuestionnaireDetailComponent} from '../questionnaire/questionnaire-detail/questionnaire-detail.component';

export const ROUTES_CONFIG = [
  {
    path: '', component: HomeComponent, pathMatch: 'full'
  },
  {
    path: 'questionnaires', component: QuestionnaireListComponent, canActivate: [UserGuardService],
    children:
      [
        {path: 'upload', component: UploadComponent, canActivate: [UserGuardService]},
      ]
  },
   {
      path: 'questionnaires/:id', component: QuestionnaireDetailComponent, canActivate: [UserGuardService],
   },
  {
    path: 'questions', component:
    QuestionListComponent, canActivate:
      [UserGuardService],
    children:
      [
        {path: 'upload', component: UploadComponent, canActivate: [UserGuardService]},
      ]
  }
  ,
  {
    path: 'upload', component:
    UploadComponent, canActivate:
      [UserGuardService]
  }
  ,
  {
    path: '**', redirectTo:
      ''
  }
]

