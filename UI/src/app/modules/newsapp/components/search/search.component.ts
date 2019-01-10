import { Component, OnInit } from '@angular/core';
import {NewsappService} from '../../newsapp.service';
import {News} from '../../news';

@Component({
  selector: 'newsapp-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  newslist :Array<News>;

  constructor(private newsService: NewsappService) {
    this.newslist=[];
   }

   onEnter(searchKey){
    this.newsService.searchNewsFromLocalRepo(searchKey).subscribe(newslist=>{
        this.newslist = newslist;
    });
}
  ngOnInit() {
    
  }
}
