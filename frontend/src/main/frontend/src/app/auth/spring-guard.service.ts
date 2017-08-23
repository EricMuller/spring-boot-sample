import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {UserService} from './user.service';
import {Observable} from 'rxjs/Rx';


@Injectable()
export class SpringGuardService implements CanActivate {

  public isLoggedIn: boolean = false;

  constructor(private userService: UserService, private router: Router) {
  }

  public logout() {
    this.isLoggedIn = false;
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

    if (this.isLoggedIn === true) {
      return Observable.of(true);
    } else {
      return new Observable<boolean>(observer => {
        this.userService.getCurrentUser().subscribe(
          (user) => {
            if (user == null) {
              observer.next(false);
            } else {
              this.isLoggedIn = true;
              observer.next(true);
            }
            observer.complete();
          },
          (error) => {
            this.isLoggedIn = false;
            observer.next(false);
            observer.complete();
          }
        )
      });
    }
  }
}
