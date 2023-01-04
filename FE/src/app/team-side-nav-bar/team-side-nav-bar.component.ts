import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { TeamMemberDashBoardComponent } from '../team-member-dash-board/team-member-dash-board.component';
import { TeamService } from '../team.service';

@Component({
  selector: 'app-team-side-nav-bar',
  templateUrl: './team-side-nav-bar.component.html',
  styleUrls: ['./team-side-nav-bar.component.css']
})
export class TeamSideNavBarComponent implements OnInit {

  constructor(private teamMemberService:TeamService,private router:Router,private teamMember:TeamMemberDashBoardComponent) { 
  }

  ngOnInit(): void {
  }
  
  name= localStorage.getItem('name')

 
 projectId:any;
  searchingItems(search:any){
    this.projectId=search;
    this.teamMember.pId=this.projectId;
    this.teamMember.searchProject();
    this.router.navigateByUrl("teamdashboard")
  }
}
