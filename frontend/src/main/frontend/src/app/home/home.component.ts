import {Component, OnInit} from '@angular/core';
import {UserService} from 'app/auth/user.service'
import {User} from 'app/auth/user.model'
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public title = 'Welcome to my  Application Sample ';

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    const obs: Observable<User> = this.userService.getCurrentUser();

    obs.subscribe((user) => {
        this.title = 'welcome ' + user.name;
      },
      (error) => {
        this.title = 'Welcome to my  Social Application Sample ';
      }
    )
  }

}
