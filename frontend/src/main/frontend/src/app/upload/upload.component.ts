import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Http, Headers, ConnectionBackend, Request, RequestOptions, RequestOptionsArgs, Response} from '@angular/http';
import {CustomHttp} from "../shared/custom.http";
import {DomSanitizer} from "@angular/platform-browser"
import {NotifierService} from "../shared/simple-notifier.service";

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  private apiEndPoint: string = '/api/questions/upload/';

  public sample =
    {
      number: 1,
      categorie: "Java Basics",
      question: "What is the difference between JDK and JRE?",
      response: "JDK stands for Java Development Kit. It contains the tools and\nlibraries for development of Java programs. It also contains\ncompilers and debuggers needed to compile Java program,\n \nJRE stands for Java Runtime Environment. This is included in JDK.\nJRE provides libraries and JVM that is required to run a Java\nprogram."
    };
  constructor(private http: CustomHttp,private http1: Http, private notifierService: NotifierService) {
  }

  ngOnInit() {

  }

  public fileChange(event) {
    let fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      let file: File = fileList[0];
      let formData: FormData = new FormData();
      formData.append('file', file, file.name);
      //let headers = new Headers();
      //headers.append('Content-Type', 'multipart/form-data');
      //headers.append('Accept', 'application/json');
      //let options = new RequestOptions({headers: headers});
      this.http.post(this.apiEndPoint, formData)
        .map(res => res.json())
        .catch(error => Observable.throw(error))
        .subscribe(
          data => this.notifierService.notifySuccess('Upload Ok'),
          error => console.log(error)
        )
    }
  }
}
