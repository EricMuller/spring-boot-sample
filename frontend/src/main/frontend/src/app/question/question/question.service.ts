import {Injectable} from '@angular/core';
import {Question} from './question.model'
import {Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';

import * as _ from 'underscore';
import {CustomHttp} from '../../shared/custom.http';

@Injectable()
export class QuestionService {
  private questions: Question[];
  private categories: string[];

  constructor(private http: CustomHttp) {
  }

  public search() {
    return this.http.get('/api/questions')
      .map(this.extractBody)
      .catch(this.handleError).share();
  }

  public extractBody(response: Response) {
    const body = response.json();
    console.log('Extract body =' + body);
    return body || {};
  }

  public handleError(error: Response | any): Observable<any> {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);

    return Observable.throw(error);
  }

}
