import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from 'app/api/api/user.service'
import {User} from './api/model/user.model';
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  public user: User;

  private subscription: Subscription;

  constructor(private userService: UserService) {
    this.subscription = this.userService.getCurrentUser()
      .subscribe(
        (user) => {
          this.user = user;
        }
      );
  }

  public ngOnInit(): void {

  }

  public logout(event): void {
    this.userService.clearUser();
  }

  public ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.subscription.unsubscribe();
  }

}
