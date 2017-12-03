import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {UserService} from './user.service';
import {Observable} from 'rxjs/Rx';
import {User} from './user.model';


@Injectable()
export class UserGuardService implements CanActivate {


  constructor(private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

    const user: User = this.userService.getUser();
    return (user == null) ? false : true;

  }

}
