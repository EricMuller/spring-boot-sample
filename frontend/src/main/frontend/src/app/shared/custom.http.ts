import {Injectable} from '@angular/core';
import {ConnectionBackend, Headers, Http, RequestOptions, RequestOptionsArgs} from '@angular/http';
import {NotifierService} from 'app/shared/simple-notifier.service'
import {Observable} from 'rxjs/Rx';
import {CookieService} from 'ngx-cookie-service';
import 'rxjs/add/operator/timeout'

@Injectable()
export class CustomHttp extends Http {

  constructor(backend: ConnectionBackend, defaultOptions: RequestOptions, private  notifierService: NotifierService,
              private cookieService: CookieService) {
    super(backend, defaultOptions);
   }

  get(url: string, timeout?: number, options?: RequestOptionsArgs): Observable<any> {
    console.log('Before the get request...');
    let default_timeout: number = 20000000;
    if (timeout != null) {
      default_timeout = timeout;
    }
    return super.get(url, this.jwt(options))
      .timeout(50000)
      .catch((response) => {
          if (response.status === 400 || response.status === 401 || response.status === 422) {
            return Observable.throw(response);
          } else {
            const json = response.json();
            this.notifierService.notifyError(json.exception);
            return Observable.empty();
          }
        }
      )
      .finally(() => {
        console.log('After the request...');
      });
  }

  post(url: string, body: any, options?: RequestOptionsArgs): Observable<any> {
    console.log('Before the post request...');
    console.log(url);
    console.log(body);
    return super.post(url, body, this.jwt(options))
      .catch((response) => {
          if (response.status === 400 || response.status === 422 || response.status === 404) {
            return Observable.throw(response);
          } else {
            const contentType = response.headers.get('Content-Type');
            if ('application/json' === contentType) {
              const json = response.json();
              this.notifierService.notifyError(json.exception);
            } else {
              this.notifierService.notifyError(response._body);
            }
            return Observable.empty();
          }
        }
      )
      .finally(() => {
        console.log('After the post request...');
      });
  }

  delete(url: string, options?: RequestOptionsArgs): Observable<any> {
    console.log('Before the delete request...');
    console.log(url);
    return super.delete(url, this.jwt(options))
      .catch((response) => {

          if (response.status === 400 || response.status === 422 || response.status === 404) {
            return Observable.throw(response);
          } else {
            const contentType = response.headers.get('Content-Type');
            if ('application/json' === contentType) {
              const json = response.json();
              this.notifierService.notifyError(json.exception);
            } else {
              this.notifierService.notifyError(response._body);
            }
            return Observable.empty();
          }
        }
      )
      .finally(() => {
        console.log('After the delete request...');
      });
  }

  private handleError(response: any) {
    if (response.status === 400 || response.status === 422) {
      return Observable.throw(response);
    } else {
      const json = response.json();
      this.notifierService.notifyError(json.exception);
      return Observable.empty();
    }
  }

  put(url: string, body: any, options?: RequestOptionsArgs): Observable<any> {
    console.log('Before the put request...');
    console.log(url);
    console.log(body);
    return super.put(url, body, this.jwt(options))
      .catch((response) => {
          if (response.status === 400 || response.status === 422) {
            return Observable.throw(response);
          } else {
            const json = response.json();
            this.notifierService.notifyError(json.exception);
            return Observable.empty();
          }
        }
      )
      .finally(() => {
        console.log('After the put request...');
      });
  }

  public jwt(options?: RequestOptionsArgs): RequestOptionsArgs {
    // create authorization header with token
    const token = this.cookieService.get('Authorization')
    // const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (token) {
      // if (currentUser && currentUser.token) {
      // Bearer
      const headers = new Headers({'Authorization': 'Bearer ' + token });
      if (options != null) {
        options.headers = headers;
      } else {
        return new RequestOptions({headers: headers});
      }
    }
    return options;
  }

}
