import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';
import {Category} from '../model/category.model';

@Injectable()
export class CategoryService {

  constructor(private http: HttpClient) { }

  public getCategories() {
    return this.http.get<Category[]>('/api/v1/categories').share();
  }


}
