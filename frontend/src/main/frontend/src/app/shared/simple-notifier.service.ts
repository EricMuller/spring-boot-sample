import {Injectable} from '@angular/core';
import {MdSnackBar} from "@angular/material";

@Injectable()
export class NotifierService {

  constructor(private mdSnackBar: MdSnackBar) {

  }

  public notifySuccess(message: string, timeOut: number = 0) {
    if (timeOut > 0) {
      this.mdSnackBar.open(message, 'Ok', {duration: timeOut});
    } else {
      this.mdSnackBar.open(message, 'Ok');
    }
  }

  public notifyError(message: string, timeOut: number = 0) {
    this.notifySuccess(message, timeOut);
  }


}
