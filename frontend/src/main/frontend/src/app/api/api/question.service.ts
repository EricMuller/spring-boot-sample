import {Injectable} from '@angular/core';
import {Question} from '../model/question.model'
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';


@Injectable()
export class QuestionService {
  private questions: Question[];
  private categories: string[];

  constructor(private http: HttpClient) {
  }

  public search() {
    return this.http.get('/api/v1/questions').catch(this.handleError).share();
  }


  public handleError(error: HttpErrorResponse | any): Observable<any> {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error.error instanceof Error) {
      errMsg = error.error.message;
    } else {
      errMsg = `Backend returned code ${error.status}, body was: ${error.error}`
    }
    console.error(errMsg);

    return Observable.throw(error);
  }

}
