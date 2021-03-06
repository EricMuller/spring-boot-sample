import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {UserService} from 'app/api/services/user.service'
import {User} from './api/model/user.model';
import {Category} from './api/model/category.model';
import {CategoryService} from './api/services/category.service';
import {Observable} from 'rxjs/Observable';
import {MatSidenav} from '@angular/material';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  public user: User;

  public categories: Observable<Category[]>;

  @ViewChild('sidenavleft')
  private sidenavleft: MatSidenav;

  constructor(private userService: UserService, private categoryService: CategoryService) {
    this.userService.getCurrentUser()
      .subscribe(
        (user) => {
          this.user = user;
          this.categories = this.categoryService.getCategories();
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
  }

}
