import { Component, OnInit } from '@angular/core';
import {NewsappService} from '../../newsapp.service';
import {News} from '../../news';

@Component({
  selector: 'newsapp-localnewslist',
  templateUrl: './localnewslist.component.html',
  styleUrls: ['./localnewslist.component.css']
})
export class LocalnewslistComponent implements OnInit {
  newslist :Array<News>;

  constructor(private newsService: NewsappService) {
    this.newslist=[];
   }

  ngOnInit() {
   // getLocalRepoNews
   this.newsService.getLocalRepoNews().subscribe((newslist)=>{
    this.newslist.push(...newslist);  
      });
  }

}
