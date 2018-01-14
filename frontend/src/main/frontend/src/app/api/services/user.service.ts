import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {User} from 'app/api/model/user.model';

import {Subject} from 'rxjs/Subject';
import {CookieService} from 'ngx-cookie-service';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class UserService {

  private user: User;

  private subject = new Subject<User>();

  constructor(private http: HttpClient, private coockieService: CookieService) {
  }

  public clearUser(): void {
    this.user = null;
    this.subject.next();
    this.coockieService.delete('Authorization', '/');
  }

  public getUser(): User {
    return this.user;
  }


  public getCurrentUser(): Observable<User> {
    if (this.user == null) {
      this.http.get('/api/v1/users/me')
        .catch(this.handleError).subscribe((user => {
          this.user = user;
          this.subject.next(user);
        }),
        error => {
          this.user = null;
          this.subject.next();
        }
      );
      return this.subject.asObservable();
    } else {
      return Observable.of(this.user);
    }

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
