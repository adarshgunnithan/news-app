import { Component, OnInit,EventEmitter} from '@angular/core';
import {Router} from '@angular/router';

import {MatSnackBar} from '@angular/material/snack-bar';

import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {MatDialogModule} from '@angular/material/dialog';
import {News} from '../../news';
import {Input,Output} from '@angular/core';
import {AuthenticationService} from '../../../authentication/authentication.service';
import { Pipe, PipeTransform } from '@angular/core';
import {DetailviewComponent} from '../detailview/detailview.component';

@Component({
  selector: 'newsapp-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
@Input()
news : News;

@Output()
addNewsEvent =new EventEmitter();
@Output()
deleteNewsEvent =new EventEmitter();

  constructor(private snackBar: MatSnackBar,private matDlg: MatDialog,public authService:AuthenticationService,private router: Router) { }

  ngOnInit() {

  }

  addNews(){
    this.addNewsEvent.emit(this.news);
  }
  deleteNews(){
    this.deleteNewsEvent.emit(this.news);
  }

  viewDetails (){
    console.log('movie is getting updated');
    let dialogRef = this.matDlg.open(DetailviewComponent,
    {
      width:"800px",
      data: {obj: this.news}
    });
   
  }
}
