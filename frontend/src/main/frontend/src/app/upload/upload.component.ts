import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Http, Headers, ConnectionBackend, Request, RequestOptions, RequestOptionsArgs, Response} from '@angular/http';
import {CustomHttp} from "../shared/custom.http";

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  private apiEndPoint: string = '/api/questions/upload/';

  constructor(private http: CustomHttp) {
  }

  ngOnInit() {
  }

  public fileChange(event) {
    let fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      let file: File = fileList[0];
      let formData: FormData = new FormData();
      formData.append('uploadFile', file, file.name);
      let headers = new Headers();
      headers.append('Content-Type', 'multipart/form-data');
      headers.append('Accept', 'application/json');
      let options = new RequestOptions({headers: headers});
      this.http.post(`${this.apiEndPoint}`, formData, options)
        .map(res => res.json())
        .catch(error => Observable.throw(error))
        .subscribe(
          data => console.log('success'),
          error => console.log(error)
        )
    }
  }
}