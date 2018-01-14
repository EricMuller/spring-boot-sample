import {Injectable} from '@angular/core';
import {Question} from '../model/question.model'
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';


@Injectable()
export class QuestionService {

  constructor(private http: HttpClient) {
  }

  public getQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>('/api/v1/questions').share();
  }

  public getQuestionsByQuestionnaireId(questionnaireId: Number): Observable<Question[]> {
    return this.http.get<Question[]>('/api/v1/questions?questionnaireId=' + questionnaireId.toString()).share();
  }

}
