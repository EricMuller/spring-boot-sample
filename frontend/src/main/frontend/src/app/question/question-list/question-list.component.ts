import {Component, OnInit} from '@angular/core';
import {Question} from 'app/api/model/question.model'
import {QuestionService} from '../../api/api/question.service';
import {NotifierService} from '../../shared/simple-notifier.service';
import {Observable} from 'rxjs/Observable';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-question',
  templateUrl: './question-list.component.html',
  styleUrls: ['./question-list.component.css']
})
export class QuestionListComponent implements OnInit {
  public title = ' Questions JAVA !';
  public number: number = 0;
  public question: Question;
  public questions: Question[] = [];

  public results: Observable<Question[]>;

  public displayedColumns = ['number', 'categorie', 'question'];
  public dataSource = new MatTableDataSource<Question>();

  ngOnInit() {
  }

  constructor(private questionService: QuestionService, private notifierService: NotifierService) {
  }

  public loadQuestion() {
    const res = this.questions.filter((element) => {
      return element.number === this.number;
    });
    this.question = res[0];
    if (this.question) {
      this.question.reponse = this.question.reponse.replace(/\n/ig, '<br>');
    }
  }

  public search() {
    this.results = this.questionService.search();

    /*this.questionService.search().subscribe(
      result => {
        this.questions.slice(0);
        for (const q of result) {
          this.questions.push(q);
        }
        console.log(result);
      },
      err => {
        console.error(err);
        this.notifierService.notifyError('Unknown error');
      });*/
  }


}
