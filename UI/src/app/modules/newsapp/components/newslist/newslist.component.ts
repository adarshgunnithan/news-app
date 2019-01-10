import { Component, OnInit } from '@angular/core';
import{NewsappService} from '../../newsapp.service'
import {News} from '../../news';

@Component({
  selector: 'newsapp-newslist',
  templateUrl: './newslist.component.html',
  styleUrls: ['./newslist.component.css']
})
export class NewslistComponent implements OnInit {
  newslist :Array<News>;
  constructor(private newsService :NewsappService) { 
   this.newslist=[];
  }

  ngOnInit() {
this.newsService.getNews().subscribe(
  (newslist)=>{
    this.newslist.push(...newslist); 
      }
 
);

  }

}
