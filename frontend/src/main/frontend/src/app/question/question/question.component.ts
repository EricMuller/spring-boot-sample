import { Component, OnInit } from '@angular/core';
import {QuestionService} from 'app/question/question.service'
import {Question} from 'app/question/model/question'
@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {
  public title = ' Questions JAVA !';
  public nbQuestion : number = 0 ;
  public number : number = 0 ;

  public question : Question;
  public categorie : string;
  public categories:any[];
  public questions: Question[];

  ngOnInit() {
  }

  constructor(private questionService:QuestionService ){
    this.questions = this.questionService.getQuestions();
    let categories = this.questionService.getCategories();
    let array =[];
    for(let categ of categories) {
      if (categ != "") {
        array.push({value: categ, viewValue: categ})
      }
    }
    this.categories =array;
  }

  public next() {
    if(this.number<this.questions.length){
      this.number++;
      this.loadQuestion();
    }
  }

  public previous() {
    if (this.number > 0) {
      this.number--;
      this.loadQuestion();
    }
  }

  public onKey(event: any) { // without type info
    this.number = event.target.value ;
    this.loadQuestion();
  }

  public clear(){
    this.number= 0;
    this.question= null;
    this.categorie= null;
    this.questions = this.questionService.getQuestions();
  }
  public onCategorieSelect(){
    this.questions = this.questionService.getQuestions().filter((question)=>{return question.categorie == this.categorie });
    this.number= 0;
    this.questions.forEach(
      (x1, index, theArray) => {
        this.questions[index].number = this.number++;
      });
    this.number= 0;
    this.loadQuestion();
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

}
