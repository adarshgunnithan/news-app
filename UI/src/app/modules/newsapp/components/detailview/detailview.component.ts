import { Component, OnInit,Inject  } from '@angular/core';
import {News} from '../../news';
import {NewsappService} from '../../newsapp.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'newsapp-detailview',
  templateUrl: './detailview.component.html',
  styleUrls: ['./detailview.component.css']
})
export class DetailviewComponent implements OnInit {
news:News;
  constructor(private dialogRef: MatDialogRef<DetailviewComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any, private newsServ: NewsappService, 
  ) {
    this.news=data.obj;
   }

  ngOnInit() {
  }

}
