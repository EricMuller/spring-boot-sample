import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {User} from 'app/auth/user.model';


import {CustomHttp} from '../shared/custom.http';

@Injectable()
export class UserService {

  public user: User;

  constructor(private http: CustomHttp) {
  }

  public getCurrentUser(): Observable<User> {
    if (this.user != null) {
      return Observable.of(this.user);
    } else {
      return this.http.get('/api/user')
        .map(this.extractBody)
        .catch(this.handleError).share();
    }
  }

  public extractBody(response: Response) {
    const body = response.json();
    this.user = body;
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
