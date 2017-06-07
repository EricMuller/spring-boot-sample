import {Injectable} from '@angular/core';
import {Question} from './model/question'

import * as data from './1000Javaquestions2017.json'
import * as _ from 'underscore';

@Injectable()
export class QuestionService {
  private questions: Question[];
  private categories: string[];

  constructor() {
    this.questions = (<any>data);
    this.categories = _.uniq(_.pluck(this.questions, 'categorie'), function (categ) {
      return categ;
    });
  }

  public getCategories(): string[] {
    return this.categories;
  }
  public getQuestions(): Question[] {
    return this.questions;
  }




}
