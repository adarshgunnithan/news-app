import { Component, OnInit, Input } from '@angular/core';
import {News} from '../../news';
import {NewsappService} from '../../newsapp.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {MatDialogModule} from '@angular/material/dialog';
import {AuthenticationService} from '../../../authentication/authentication.service';

@Component({
  selector: 'newsapp-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  newslist :Array<News>
  constructor(private newsService :NewsappService,private matSnackBar :MatSnackBar, private authSer :AuthenticationService) { 

  }

  addNews(news){
    const isAdmin =this.authSer.isAdmin();
    

    if(isAdmin){
    this.newsService.addNews(news).subscribe((news)=>{
      this.matSnackBar.open('News added to application!','',{duration:1000});
    });
  }else{
    this.newsService.addToWatchList(news).subscribe((news)=>{
      this.matSnackBar.open('News added to watchlist!','',{duration:1000});
    });
  }
  }

  deleteNews(news){
    const isAdmin =this.authSer.isAdmin();
   

  if(isAdmin){
    this.newsService.deleteFromLocalRepo(news).subscribe((news)=>{
      this.matSnackBar.open('News deleted from application!','',{duration:2000})
    },(err) => {
      console.log("error message",err.error);
      this.matSnackBar.open(err.error,'',{duration:1000})
    });
  }else{
    this.newsService.deleteFromWatchList(news).subscribe((news)=>{
      this.matSnackBar.open('News deleted from watchlist!','',{duration:1000})
    });
  }
  for (var i = 0; i < this.newslist.length; i++) {
    if (this.newslist[i].title == news.title) {
      this.newslist.splice(i, 1);
    }
  }
  }

  ngOnInit() { 
  }

}
