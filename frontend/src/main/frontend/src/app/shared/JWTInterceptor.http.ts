import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {Observable} from 'rxjs/Observable';
import {NotifierService} from 'app/shared/simple-notifier.service';


@Injectable()
export class JWTInterceptor implements HttpInterceptor {

  constructor(private  notifierService: NotifierService, private cookieService: CookieService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const JWT = `Bearer ${this.cookieService.get('Authorization')}`;
    req = req.clone({
      setHeaders: {
        Authorization: JWT
      }
    });

    return next.handle(req).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        const contentType = event.headers.get('Content-Type');
        if (event.status === 401 || event.status === 400 || event.status === 422) {
          if ('application/json' === contentType) {
            this.notifierService.notifyError(event.body.exception);
          } else {
            this.notifierService.notifyError(event.body);
          }
          return Observable.empty();
        }
      }
    }).catch(err => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401 || err.status === 400 || err.status === 422) {
            return Observable.throw(err);
          }
        } else {
          this.notifierService.notifyError(err.exception);
          return Observable.empty();
        }
      }
    )
  }


}
