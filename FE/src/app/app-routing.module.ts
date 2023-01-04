import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { AuthGuard } from './auth.guard';
import { HomeComponent } from './home/home.component';
import { KanabanHomeComponent } from './kanaban-home/kanaban-home.component';
import { LoginComponent } from './login/login.component';
import { SideNavBarComponent } from './side-nav-bar/side-nav-bar.component';
import { SignUpDialogComponent } from './sign-up-dialog/sign-up-dialog.component';
import { TeamMemberDashBoardComponent } from './team-member-dash-board/team-member-dash-board.component';
import { TeamSideNavBarComponent } from './team-side-nav-bar/team-side-nav-bar.component';



const routes: Routes = [
  {path:"",component:HomeComponent},
  {path:"login",component:LoginComponent},
  {path:"signup",component:SignUpDialogComponent},
  {path:"logout",component:HomeComponent},
  {path:"home",component:HomeComponent,},
  {path:"kanban",component:KanabanHomeComponent,  canActivate:[AuthGuard]},
  
    {path:"about", component:AboutUsComponent,},

  {path:"teamdashboard",component:TeamMemberDashBoardComponent,canActivate:[AuthGuard] } 

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
