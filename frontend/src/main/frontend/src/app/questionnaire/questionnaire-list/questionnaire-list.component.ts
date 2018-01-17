import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSidenav, MatTableDataSource} from '@angular/material';
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

  public questionnaire: Questionnaire = new Questionnaire();

  public displayedColumns = ['title', 'description'];
  public dataSource = new MatTableDataSource<Questionnaire>();

  public categoryControl = new FormControl('', [Validators.required]);

  public animals = [
    {name: 'Dog', sound: 'Woof!'},
    {name: 'Cat', sound: 'Meow!'},
    {name: 'Cow', sound: 'Moo!'},
    {name: 'Fox', sound: 'Wa-pa-pa-pa-pa-pa-pow!'},
  ];

  @ViewChild('sidenavright')
  private sidenavright: MatSidenav;


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

  public hide() {
    console.log(this.questionnaire);
    this.sidenavright.toggle();
  }

  public save(q: Questionnaire) {
    this.questionnaireService.postQuestionnaire(q).subscribe((result) => {
      this.questionnaire = result;
      this.sidenavright.toggle();
      this.notifierService.notifySuccess(result.title, 2000);
    });
  }

  public edit(q: Questionnaire) {
    this.questionnaireService.getQuestionnaireById(q.id).subscribe((result) => {
      this.questionnaire = result;
      this.sidenavright.toggle();
    });

  }

  compareById(f1: any, f2: any) {
    return f1 && f2 && f1.id === f2.id;
  }

}
