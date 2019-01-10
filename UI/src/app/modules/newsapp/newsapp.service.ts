import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {News} from './news';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class NewsappService {

  getMovieFromEndpoint :string;
  apiKey : string;
  baseRepoUrl: string;
  newsLocalRepoUrl :string;
  watchListUrl:string;
  deleteFromLocalRepoUrl:string;
//GET /api/v1/watchlist
  constructor(private http:HttpClient) { 
    this.apiKey="e4b41bc6acf248d19f90e1617d411beb";
    this.baseRepoUrl="https://newsapi.org/v2/";
    this.getMovieFromEndpoint=
    this.getMovieFromEndpoint="https://newsapi.org/v2/top-headlines";
    this.newsLocalRepoUrl="http://localhost:8081/api/v1/news";
    this.deleteFromLocalRepoUrl="http://localhost:8081/api/v1/news/delete";
    this.watchListUrl="http://localhost:8081/api/v1/watchlist";
  }
//https://newsapi.org/v2/everything?q=%3C%3Csearch_text%3E%3E&apiKey=e4b41bc6acf248d19f90e1617d411beb&language=en&page=1

 
  //get movies from third party
  getNews():Observable<Array<News>>{
      //https://newsapi.org/v2/top-headlines?country=in&apikey=e4b41bc6acf248d19f90e1617d411beb&page=1
    const url=`${this.getMovieFromEndpoint}?country=in&apikey=${this.apiKey}&page=1`;
    return this.http.get<Array<News>>(url).pipe(
      map(this.pickMovieResults)
  
  );
  }

  //get from local
  getLocalRepoNews():Observable<Array<News>>{
    return this.http.get<Array<News>>(this.newsLocalRepoUrl);
  }

  //get from watchlist
  getMyNews():Observable<Array<News>>{
    return this.http.get<Array<News>>(this.watchListUrl);
  }
  
  
  // add movies to local repo by admin
  addNews(news){
   return this.http.post(this.newsLocalRepoUrl,news);
  }
  addToWatchList(news){
    return this.http.post(this.watchListUrl,news);
  }
  
  pickMovieResults(response) {
    return response['articles'];
  }

  deleteFromLocalRepo(news){
  const url =`${this.deleteFromLocalRepoUrl}/${news.newsId}`;
  return this.http.delete(url,{responseType:'text'});
  }


  deleteFromWatchList(news){
  const url =`${this.watchListUrl}/${news.newsId}`;
  return this.http.delete(url,{responseType:'text'});
  }


  searchNewsFromLocalRepo(searchKey: string, page: number = 1): Observable<Array<News>> {
    if (searchKey.length > 0) {
    const url=`${this.newsLocalRepoUrl}/search/${searchKey}`;
    return this.http.get<Array<News>>(url);
    }
    return null;
  }
}
