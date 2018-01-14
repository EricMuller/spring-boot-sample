import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {NotifierService} from '../../shared/simple-notifier.service';
import {Observable} from 'rxjs/Observable';
import {QuestionnaireService} from '../../api/services/questionnaire.service';
import {Questionnaire} from '../../api/model/questionnaire.model';


@Component({
  selector: 'app-questionnaire-list',
  templateUrl: './questionnaire-list.component.html',
  styleUrls: ['./questionnaire-list.component.css']
})
export class QuestionnaireListComponent implements OnInit {


  public results: Observable<Questionnaire[]>;

  public displayedColumns = ['title', 'description'];
  public dataSource = new MatTableDataSource<Questionnaire>();

  constructor(private questionnaireService: QuestionnaireService, private notifierService: NotifierService) {
  }

  ngOnInit() {
    this.results = this.questionnaireService.getQuestionnaires();
  }

  public search() {
    this.results = this.questionnaireService.getQuestionnaires();
  }


}
