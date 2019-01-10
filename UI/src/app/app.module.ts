import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {AuthenticationModule} from './modules/authentication/authentication.module';

import { AppComponent } from './app.component';
import {NewsappModule} from './modules/newsapp/newsapp.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {Routes,RouterModule} from '@angular/router';
//import {AuthGuardService} from './authGuard.service';

import { MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';

const appRoutes :Routes =[
  {
    path :'',
    redirectTo:'login',
    pathMatch:'full'
  }
  ];
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    NewsappModule,
    MatToolbarModule,
    MatButtonModule,
    BrowserAnimationsModule,
    AuthenticationModule,
    MatDialogModule,
    RouterModule.forRoot(appRoutes)
    
  ],
//  providers: [AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
