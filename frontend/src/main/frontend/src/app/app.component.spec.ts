import {async, TestBed} from '@angular/core/testing';

import {AppComponent} from './app.component';
import {MatCheckboxModule, MatIconModule, MatListModule, MatMenuModule, MatSidenavModule, MatToolbarModule} from '@angular/material';
import {RouterModule} from '@angular/router';
import {UserService} from './api';
import {HttpClientModule} from '@angular/common/http';
import {CookieService} from 'ngx-cookie-service';
import {CategoryService} from './api/services/category.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterTestingModule} from '@angular/router/testing';
import {User} from './api/model/user.model';
import {Observable} from 'rxjs/Rx';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MatIconModule,
        MatMenuModule,
        MatToolbarModule,
        MatCheckboxModule,
        MatListModule,
        MatSidenavModule,
        RouterModule,
        HttpClientModule,
        BrowserAnimationsModule,
        RouterTestingModule
      ],
      declarations: [
        AppComponent
      ],
      providers: [CookieService, CategoryService, {provide: UserService, useClass: MockUserService}]
    }).compileComponents();
  }));

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

  it(`should have as title 'app'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.user.name).toEqual('eric');
  }));

});

class MockUserService {
  public getCurrentUser(): Observable<User> {
    const user: User = new User();
    user.name = 'eric';
    return Observable.of(user);
  }
};
