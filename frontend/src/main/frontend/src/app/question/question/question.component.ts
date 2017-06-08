import {Component, OnInit} from '@angular/core';
import {Question} from 'app/question/question/question'
import {QuestionService} from "./question.service";
import {NotifierService} from "../../shared/simple-notifier.service";


@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {
  public title = ' Questions JAVA !';
  public number: number = 0;
  public question: Question;
  public questions: Question[] =[];

  ngOnInit() {
  }

  constructor(private questionService: QuestionService, private notifierService: NotifierService) {
  }

  public loadQuestion() {
    let res = this.questions.filter((element) => {
      return element.number == this.number;
    });
    this.question = res[0];
    if (this.question) {
      this.question.reponse = this.question.reponse.replace(/\n/ig, "<br>");
    }
  }

  public search() {
    this.questionService.search().subscribe(
      result => {
        //this.pushBookmarks(result)
        this.questions.slice();
        for(let q of result ){
          this.questions.push(q);
        }
        console.log(result);
      },
      err => {
        console.error(err);
      });
    //this.notifierService.notifySuccess("dd");
  }


}
