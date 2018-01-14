import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Questionnaire} from '../model/questionnaire.model';

@Injectable()
export class QuestionnaireService {

  constructor(private http: HttpClient) {
  }

  public getQuestionnaires() {
    return this.http.get<Questionnaire[]>('/api/v1/questionnaires').share();
  }

  public getQuestionnaireById(id: Number) {
    return this.http.get<Questionnaire>('/api/v1/questionnaires/' + id.toString()).share();
  }

}
