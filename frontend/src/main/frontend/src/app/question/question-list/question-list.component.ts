import {Component, OnInit} from '@angular/core';
import {Question} from 'app/api/model/question.model'
import {NotifierService} from '../../shared/simple-notifier.service';
import {Observable} from 'rxjs/Observable';
import {MatTableDataSource} from '@angular/material';
import {QuestionService} from '../../api';
import {ActivatedRoute} from '@angular/router';

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

  private sub: any;
  id: number;

  constructor(private questionService: QuestionService, private notifierService: NotifierService, private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id']; // (+) converts string 'id' to a number
      this.getQuestions(this.id);
    });
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

  public getQuestions(questionnaireId: Number) {
    console.log(questionnaireId);
    this.results = this.questionService.getQuestionsByQuestionnaireId(questionnaireId);

    /*this.questionService.getQuestions().subscribe(
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
