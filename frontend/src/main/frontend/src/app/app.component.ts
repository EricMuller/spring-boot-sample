import {Component, OnInit} from '@angular/core';
import {UserService} from 'app/auth/user.service'
import {User} from './auth/user.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public user: User;

  constructor(private userService: UserService) {
  }

  public ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(
      (user) => {
        this.user = user;
      }
    );
  }


}
