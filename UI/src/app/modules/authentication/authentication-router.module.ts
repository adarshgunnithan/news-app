import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';

const authRoutes: Routes =[
    {
        path:'',
         children:[
             {
                path:'',
                redirectTo: '/login',
                pathMatch:'full'
             },
             {
                path:'register',
                component:RegisterComponent,
                
             },
             {
                path:'login',
                component:LoginComponent,
             }
         ]
    }
];

@NgModule({
    imports: [
            RouterModule.forChild(authRoutes),
    ],
    exports: [
        RouterModule,
    ]
})
export class AuthenticationRoutingModule{

}