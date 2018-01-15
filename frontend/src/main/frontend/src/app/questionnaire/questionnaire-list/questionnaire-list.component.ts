import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {NotifierService} from '../../shared/simple-notifier.service';
import {Observable} from 'rxjs/Observable';
import {QuestionnaireService} from '../../api/services/questionnaire.service';
import {Questionnaire} from '../../api/model/questionnaire.model';
import {FormControl, Validators} from '@angular/forms';
import {CategoryService} from '../../api/services/category.service';
import {Category} from '../../api/model/category.model';


@Component({
  selector: 'app-questionnaire-list',
  templateUrl: './questionnaire-list.component.html',
  styleUrls: ['./questionnaire-list.component.css']
})
export class QuestionnaireListComponent implements OnInit {


  public questionnaires: Observable<Questionnaire[]>;

  public categories: Observable<Category[]>;

  public displayedColumns = ['title', 'description'];
  public dataSource = new MatTableDataSource<Questionnaire>();

  public categoryControl = new FormControl('', [Validators.required]);

  public animals = [
    {name: 'Dog', sound: 'Woof!'},
    {name: 'Cat', sound: 'Meow!'},
    {name: 'Cow', sound: 'Moo!'},
    {name: 'Fox', sound: 'Wa-pa-pa-pa-pa-pa-pow!'},
  ];

  constructor(private questionnaireService: QuestionnaireService, private notifierService: NotifierService,
              private categorieService: CategoryService) {
  }

  ngOnInit() {
    this.questionnaires = this.questionnaireService.getQuestionnaires();
    this.categories = this.categorieService.getCategories();
  }

  public search() {
    this.questionnaires = this.questionnaireService.getQuestionnaires();
  }


}
