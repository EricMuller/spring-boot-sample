import {HomeComponent} from "../home/home.component";
import {QuestionComponent} from "../question/question/question.component";
import {UploadComponent} from "../upload/upload.component";


export const ROUTES_CONFIG = [
  {
    path: '', component: HomeComponent, pathMatch: 'full'
  },
  {
    path: 'questions', component: QuestionComponent,
    children: [
      {path: 'upload', component: UploadComponent},
    ]
  },
  {path: 'upload', component: UploadComponent},
  {path: '**', redirectTo: ''}
]

