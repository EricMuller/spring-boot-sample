import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material';

@Injectable()
export class NotifierService {

  constructor(private mdSnackBar: MatSnackBar) {

  }

  public notifySuccess(message: string, timeOut: number = 0) {
    if (timeOut > 0) {
      this.mdSnackBar.open(message, 'Ok', {duration: timeOut});
    } else {
      this.mdSnackBar.open(message, 'Ok');
    }
  }

  public notifyError(message: string, timeOut: number = 0) {
    this.mdSnackBar.open(message, 'ERROR');
  }


}
