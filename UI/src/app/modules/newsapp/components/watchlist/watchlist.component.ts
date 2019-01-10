import { Component, OnInit } from '@angular/core';
import {NewsappService} from '../../newsapp.service';
import {News} from '../../news';

@Component({
  selector: 'newsapp-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  newslist :Array<News>;

  constructor(private newsService: NewsappService) {
    this.newslist=[];
   }




  ngOnInit() {
    this.newsService.getMyNews().subscribe((newslist)=>{
     this.newslist.push(...newslist);  
       });
  }

}
