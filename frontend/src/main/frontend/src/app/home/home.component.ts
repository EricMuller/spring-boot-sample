import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from 'app/auth/user.service'
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {


  public title;

  private subscription: Subscription;

  constructor(private userService: UserService) {

    const MESSAGE = 'Welcome to my  Application Sample ';


    this.subscription = this.userService.getCurrentUser()
      .subscribe((user) => {
          if (user) {
            this.title = 'welcome ' + user.name;
          } else {
            this.title = MESSAGE;
          }
        },
        (error) => {
          this.title = MESSAGE;
        }
      )

  }

  ngOnInit() {

  }

  public ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.subscription.unsubscribe();
  }

}
