import {CustomHttp} from './custom.http'
import {NotifierService} from './simple-notifier.service'
import {RequestOptions, XHRBackend} from '@angular/http';
import {CookieService} from 'ngx-cookie-service';

/*export let httpFactory = (backend: XHRBackend, defaultOptions: RequestOptions, notifier:NotifierService) => {
  return new CustomHttp(backend, defaultOptions, notifier);
};*/

export function httpFactory(backend: XHRBackend, defaultOptions: RequestOptions, notifier: NotifierService, cookieService: CookieService) {
  return new CustomHttp(backend, defaultOptions, notifier, cookieService);
}

export let HTTP_PROVIDER =
  {
    provide: CustomHttp,
    useFactory: httpFactory,
    deps: [XHRBackend, RequestOptions, NotifierService, CookieService]
  };
