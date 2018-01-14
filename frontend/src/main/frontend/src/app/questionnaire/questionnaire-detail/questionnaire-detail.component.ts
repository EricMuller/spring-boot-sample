import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {NotifierService} from '../../shared/simple-notifier.service';
import {QuestionService} from '../../api';
import {Observable} from 'rxjs/Observable';
import {Question} from '../../api/model/question.model';
import {Questionnaire} from '../../api/model/questionnaire.model';
import {QuestionnaireService} from '../../api/services/questionnaire.service';

@Component({
  selector: 'app-questionnaire-detail',
  templateUrl: './questionnaire-detail.component.html',
  styleUrls: ['./questionnaire-detail.component.css']
})
export class QuestionnaireDetailComponent implements OnInit {


  public questionnaire: Questionnaire = new Questionnaire();
  public results: Observable<Question[]>;

  private sub: any;
  id: number;

  constructor(private questionService: QuestionService, private questionnaireService: QuestionnaireService,
              private notifierService: NotifierService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id']; // (+) converts string 'id' to a number
      this.questionnaireService.getQuestionnaireById(this.id).subscribe(
        (questionnaire) => {
          this.questionnaire = questionnaire;
          this.getQuestions(this.questionnaire.id);
        }
      );

    });
  }

  public getQuestions(questionnaireId: Number) {

    this.results = this.questionService.getQuestionsByQuestionnaireId(questionnaireId);

  }

}
