import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContainerComponent } from './components/container/container.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { NewslistComponent } from './components/newslist/newslist.component';
import {NewsappService} from './newsapp.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import{MatCardModule} from '@angular/material/card';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { LocalnewslistComponent } from './components/localnewslist/localnewslist.component';
import {NewsAppRoutingModule} from '../newsapp/newsapp-router.module';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchComponent } from './components/search/search.component';
import {TokenInterceptor} from './interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import {AuthGuardService} from '../../authGuard.service';
import { DetailviewComponent } from './components/detailview/detailview.component';

@NgModule({
  imports: [
    CommonModule,HttpClientModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    MatInputModule,
    NewsAppRoutingModule
  ],
  entryComponents:[DetailviewComponent],
  declarations: [ContainerComponent, ThumbnailComponent, NewslistComponent, LocalnewslistComponent, WatchlistComponent, SearchComponent,DetailviewComponent],
  exports:[ContainerComponent, ThumbnailComponent, NewslistComponent,LocalnewslistComponent,NewsAppRoutingModule, WatchlistComponent, SearchComponent,DetailviewComponent],
  providers:[NewsappService,{
    provide:HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi:true
  },AuthGuardService
   ]
})
export class NewsappModule { }
