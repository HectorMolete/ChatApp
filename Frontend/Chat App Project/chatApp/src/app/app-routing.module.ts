import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SigninComponent } from './signin/signin.component';
import { ChatsPageComponent } from './chats-page/chats-page.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AuthGuardService } from './service/auth.guard';


const routes: Routes = [
  {path: 'log-in', component : LoginComponent},
  {path: 'sign-in', component : SigninComponent},
  {path: 'chats', component : ChatsPageComponent, canActivate: [AuthGuardService]},
  {path: '', redirectTo : 'log-in', pathMatch:'full'},
  {path: '**', component : NotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
